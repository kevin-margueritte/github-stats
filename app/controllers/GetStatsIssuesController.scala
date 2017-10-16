package controllers

import javax.inject.Inject

import com.github.nscala_time.time.Imports._
import io.circe.Json
import io.circe.optics.JsonPath.root
import io.circe.syntax._
import models.Coordinate
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.libs.ws.WSClient
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future}

/**
  * Ce controlleur calcul le nombre d'issues par jour d'un repo sur les 30 derniers jours
  *
  * @param cc
  * @param ws
  * @param ec
  */
class GetStatsIssuesController @Inject()(cc: ControllerComponents, ws: WSClient)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd")

  def getStatsIssues(owner : String, repository : String) = Action.async { implicit request: Request[AnyContent] =>

    getDatesIssues(owner, repository).map { listDates =>

      // Suppression de la liste les dates inferieurs a 30 jours
      val datesFilter = listDates.filter(_ > DateTime.now - 30.days)

      val stats = computeStatistics(datesFilter)
      val coordinates = stats.toSeq.map(x => Coordinate(x._1.toString(dateFormat), x._2)).sortBy(_.x)

      Ok(coordinates.asJson.spaces2)

    }
  }

  /**
    * Methode calculant pour chaque jour le nombre d'issues
    *
    * @param coordinates
    * @return
    */
  def computeStatistics(coordinates : List[DateTime]) : Map[DateTime, Int] = {
    // Tail recursion pour calculer le nombre d'issues par jour
    @tailrec def computeStatistics(currentDate : DateTime, coordinates : Map[DateTime, Int]) : Map[DateTime, Int] = {
      currentDate match {
        case date if date > DateTime.now => coordinates // La date passee en parametre est superieur a la date du jour
        case date => {
          coordinates.exists(_._1.equals(currentDate)) match {
            case true => computeStatistics(currentDate + 1.day, coordinates) // La date existe deja on passe au jour suivant
            case false => computeStatistics(currentDate + 1.day, coordinates + (currentDate -> 0)) // Aucun issue a ce jour, on l'ajoute dans la map
          }
        }
      }
    }

    // Creation d'une map avec comme cle la date et comme valeur son nombre d'occurence
    val groupDates = coordinates.groupBy(identity).mapValues(_.size)

    // Calcul du nombre d'isues les 30 derniers jours
    val nowString = DateTime.now.toString(dateFormat)
    computeStatistics(dateFormat.parseDateTime(nowString) - 30.days, groupDates)
  }

  /**
    * Recuperation des dates des issues des 30 derniers jours
    *
    * @param owner
    * @param repository
    * @return
    */
  def getDatesIssues(owner : String, repository : String) : Future[List[DateTime]] = {

    //Je n'arrive a pas a resoudre un enchainement d'appels WS, cet exercice est faux,
    // s'il y plus de cents issues sur 30j. Le meilleur moyen est de récuperer toutes les pages

    /*@tailrec def getIssues(url : Option[String], acc : List[String]) : Future[List[String]] = {
      url match {
        case None => Future.successful(acc)
        case Some(url) => {
          ws.url(url).get.map { response =>
            val doc = io.circe.parser.parse(response.json.toString().mkString).getOrElse(Json.Null)
            val listDate = root.each.created_at.string.getAll(doc) ++ acc

            response.header("Link") match {
              case Some(urlHyperMedia) => {
                val pagePattern = """<(.*)>; rel="next"""".r
                val urlNext = pagePattern.findAllIn(urlHyperMedia).group(1).toString
                getIssues(Some(urlNext), listDate)
              }
              case _ => getIssues(None, listDate)
            }
          }
        }
      }
    }*/

    // Un parametre 'since' existe mais fonctionne avec les dates de maj
    val url = s"""https://api.github.com/repos/${owner}/${repository}/issues?sort=created_date&per_page=100"""

    ws.url(url).get.map { response =>
      val doc = io.circe.parser.parse(response.json.toString().mkString).getOrElse(Json.Null)
      root.each.created_at.string.getAll(doc).map{ dateString =>

        // Parse de la date pour recuperer le format YYYY-MM-DD
        val datePattern = """(.*)T""".r
        val dateExtracted = datePattern.findAllIn(dateString).group(1).toString

        dateFormat.parseDateTime(dateExtracted)

      }
    }
  }

}
