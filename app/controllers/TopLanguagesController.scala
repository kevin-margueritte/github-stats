package controllers

import javax.inject.Inject

import io.circe.Json
import io.circe.optics.JsonPath.root
import io.circe.syntax._
import models.RepositoryLanguage
import play.api.libs.ws.WSClient
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import scala.concurrent.{ExecutionContext, Future}


/**
  * Ce controller retourne le top 10 des langages les plus utilisés par un utilisateur
  * Le calcule est effectue de la maniee suivante :
  *   - Recuperation de tous les repo. de l'utilisateur
  *   - Pour chaque depot calcul du nombre commits de l'utilisateur
  *
  * @param cc
  * @param ws
  * @param ec
  */
class TopLanguagesController @Inject()(cc: ControllerComponents, ws: WSClient)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  def topLanguages(user : String) = Action.async { implicit request: Request[AnyContent] =>
    val url = s"""https://api.github.com/search/repositories?q=user:${user}"""

    val resultAction = for {
      repositories <- getRepositories(url)
      groupedLanguages <- countCommitsByLanguage(user, repositories)
    } yield groupedLanguages

    resultAction.map{ result =>
      Ok(result.asJson.spaces2)
    }

  }

  /**
    * Retourne une map contenant en clé le langage majoritaire et le nombre de commits
    *
    * @param user
    * @param repoLang
    * @return
    */
  def countCommitsByLanguage(user : String, repoLang : List[RepositoryLanguage]) : Future[Seq[String]] = {

    val action = repoLang.map {
      rl => {
        // Iteration sur les stats du repo. pour recuperer le nombre commits
        // /!\ Github autorise 30 appels, ne pas utiliser des gros repositories
        countCommitsByRepository(user, rl.repository).map { count =>
          rl.language -> count
        }
      }
    }

    Future.sequence(action).map { result =>
      // On regroupe le resultat dans une map avec comme clé le langage et en valeur le nombre de commit
      val mapLangNbCommit = result.groupBy(_._1).mapValues(v => v.map(_._2)).map( x => (x._1, x._2.reduce((x,y) => x + y) ))

      // Recuperation des 10 langages les plus utilisés
      mapLangNbCommit.toSeq.sortBy(_._2).reverse.take(10).map(_._1)
    }

  }

  /**
    * Get repositories et les regroupes avec leur langage majoritaire
    * @param url
    * @return
    */
  def getRepositories(url : String) : Future[List[RepositoryLanguage]] = {
    ws.url(url).get.map{response =>
      // Parse de la reponse
      val doc = io.circe.parser.parse(response.json.toString().mkString).getOrElse(Json.Null)

      // Recuperation de la liste RepositoryLanguage
      root.items.each.as[RepositoryLanguage].getAll(doc)
    }
  }

  /**
    * Compte le nombre de commits du repository
    *
    * @param user
    * @param repository
    * @return
    */
  def countCommitsByRepository(user : String, repository : String) : Future[Int] = {
    val url = s"""https://api.github.com/repos/${user}/${repository}/stats/participation"""

    ws.url(url).get.map{response =>
      // Parse de la reponse
      val doc = io.circe.parser.parse(response.json.toString().mkString).getOrElse(Json.Null)

      // Recuperation de la liste du nombre de commits par jour
      val commitsList = root.owner.each.int.getAll(doc)
      commitsList match {
        case commits if !commits.isEmpty => commits.reduceLeft((x,y) => x + y)
        case _ => 0
      }
    }
  }

}
