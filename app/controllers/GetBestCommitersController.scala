package controllers

import javax.inject.Inject

import io.circe._
import io.circe.optics.JsonPath.root
import io.circe.syntax._
import models.Author
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Retourne les 10 plus importants commiters d'un repo.
  * La recherche s'effectue sur les 100 premiers commits du repo
  *
  * @param cc
  * @param ws
  * @param ec
  */
class GetBestCommitersController @Inject()(cc: ControllerComponents, ws: WSClient)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  def getBestCommiters(owner : String, repository : String) = Action.async { implicit request: Request[AnyContent] =>

    val url = s"https://api.github.com/repos/${owner}/${repository}/commits?per_page=100"

    val action = for {
      page <- getPageFromHyperMedia(url) // Recuperation du numero de la derniere page
      commitsLastPage <- getCommitByPage(url, page) // Recuperation des commits sur la derniere page
      commitsPageBeforeLast <- getCommitByPage(url, page - 1) // Recuperation des commits sur l'avant derniere page
    } yield commitsLastPage ::: commitsPageBeforeLast // Concatenation des resultats, le tri par date persiste

    action.map {
      _ match {
        case l : List[Author] => {
          val hundredCommits = l.takeRight(100) // Recuperation des cents derniers commits pour garder le tri
          val commitsGrouped = hundredCommits.groupBy( commit => (commit) ).
            mapValues(l => l.size) // Map des commits avec pour clé le nom et l'email du commiteur, la valeur et son nombre de contributions
          val topCommits = commitsGrouped.toSeq.sortWith(_._2 > _._2).map(_._1).take(10) // Trie par ordre croissant du nombre de commits, puis recuperation des 10 premiers
          Ok(topCommits.asJson.spaces2)
        }
      }
    }
  }

  /**
    * Methode retournant la premiere page à partir de l'hypermedia renvoyé
    *
    * @param url
    * @return
    */
  def getPageFromHyperMedia(url : String) : Future[Int] = {
    ws.url(url).get.map{response =>
      response.header("Link") match {
        case Some(urlHyperMedia) => { // Récupération de la page la plus grande
          val pagePattern = """page=(\d+)>; rel="last"""".r
          pagePattern.findAllIn(urlHyperMedia).group(1).toInt
        }
        case _ => 1 // Aucun hypermedia est retourne, il n'y a donc qu'une page sur le repo
      }
    }
  }

  /**
    * Methode retournant les commits par page
    *
    * @param url
    * @param page
    * @return
    */
  def getCommitByPage(url : String, page : Int) : Future[List[Author]] = {
    page match {
      case x if x > 0 => {
        ws.url(url).addQueryStringParameters(("page", page.toString)).get().map { response =>
          // Navigation dans le Json et parse des informations du commiteur
          val doc = io.circe.parser.parse(response.json.toString().mkString).getOrElse(Json.Null)
          root.each.commit.author.as[Author].getAll(doc)
        }
      }
      case _ => Future.successful(List[Author]())
    }

  }

}
