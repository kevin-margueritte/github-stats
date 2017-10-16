
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kevinmargueritte/Documents/graphQL/conf/routes
// @DATE:Mon Oct 16 08:35:57 CEST 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  GetBestCommitersController_1: controllers.GetBestCommitersController,
  // @LINE:10
  TopLanguagesController_3: controllers.TopLanguagesController,
  // @LINE:13
  GetStatsIssuesController_0: controllers.GetStatsIssuesController,
  // @LINE:16
  Assets_2: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    GetBestCommitersController_1: controllers.GetBestCommitersController,
    // @LINE:10
    TopLanguagesController_3: controllers.TopLanguagesController,
    // @LINE:13
    GetStatsIssuesController_0: controllers.GetStatsIssuesController,
    // @LINE:16
    Assets_2: controllers.Assets
  ) = this(errorHandler, GetBestCommitersController_1, TopLanguagesController_3, GetStatsIssuesController_0, Assets_2, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, GetBestCommitersController_1, TopLanguagesController_3, GetStatsIssuesController_0, Assets_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """commiters/""" + "$" + """owner<[^/]+>/""" + "$" + """repository<[^/]+>""", """controllers.GetBestCommitersController.getBestCommiters(owner:String, repository:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """user/""" + "$" + """user<[^/]+>/languages""", """controllers.TopLanguagesController.topLanguages(user:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """issues/""" + "$" + """owner<[^/]+>/""" + "$" + """repository<[^/]+>""", """controllers.GetStatsIssuesController.getStatsIssues(owner:String, repository:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix, """controllers.Assets.at(path:String = "/public", file:String = "index.html")"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_GetBestCommitersController_getBestCommiters0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("commiters/"), DynamicPart("owner", """[^/]+""",true), StaticPart("/"), DynamicPart("repository", """[^/]+""",true)))
  )
  private[this] lazy val controllers_GetBestCommitersController_getBestCommiters0_invoker = createInvoker(
    GetBestCommitersController_1.getBestCommiters(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GetBestCommitersController",
      "getBestCommiters",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """commiters/""" + "$" + """owner<[^/]+>/""" + "$" + """repository<[^/]+>""",
      """ Get best commits""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_TopLanguagesController_topLanguages1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("user/"), DynamicPart("user", """[^/]+""",true), StaticPart("/languages")))
  )
  private[this] lazy val controllers_TopLanguagesController_topLanguages1_invoker = createInvoker(
    TopLanguagesController_3.topLanguages(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TopLanguagesController",
      "topLanguages",
      Seq(classOf[String]),
      "GET",
      this.prefix + """user/""" + "$" + """user<[^/]+>/languages""",
      """ Top 10 languages by user""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_GetStatsIssuesController_getStatsIssues2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("issues/"), DynamicPart("owner", """[^/]+""",true), StaticPart("/"), DynamicPart("repository", """[^/]+""",true)))
  )
  private[this] lazy val controllers_GetStatsIssuesController_getStatsIssues2_invoker = createInvoker(
    GetStatsIssuesController_0.getStatsIssues(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GetStatsIssuesController",
      "getStatsIssues",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """issues/""" + "$" + """owner<[^/]+>/""" + "$" + """repository<[^/]+>""",
      """ Get issues stats by day""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Assets_versioned3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned3_invoker = createInvoker(
    Assets_2.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Assets_at4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Assets_at4_invoker = createInvoker(
    Assets_2.at(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_GetBestCommitersController_getBestCommiters0_route(params@_) =>
      call(params.fromPath[String]("owner", None), params.fromPath[String]("repository", None)) { (owner, repository) =>
        controllers_GetBestCommitersController_getBestCommiters0_invoker.call(GetBestCommitersController_1.getBestCommiters(owner, repository))
      }
  
    // @LINE:10
    case controllers_TopLanguagesController_topLanguages1_route(params@_) =>
      call(params.fromPath[String]("user", None)) { (user) =>
        controllers_TopLanguagesController_topLanguages1_invoker.call(TopLanguagesController_3.topLanguages(user))
      }
  
    // @LINE:13
    case controllers_GetStatsIssuesController_getStatsIssues2_route(params@_) =>
      call(params.fromPath[String]("owner", None), params.fromPath[String]("repository", None)) { (owner, repository) =>
        controllers_GetStatsIssuesController_getStatsIssues2_invoker.call(GetStatsIssuesController_0.getStatsIssues(owner, repository))
      }
  
    // @LINE:16
    case controllers_Assets_versioned3_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned3_invoker.call(Assets_2.versioned(path, file))
      }
  
    // @LINE:17
    case controllers_Assets_at4_route(params@_) =>
      call(Param[String]("path", Right("/public")), Param[String]("file", Right("index.html"))) { (path, file) =>
        controllers_Assets_at4_invoker.call(Assets_2.at(path, file))
      }
  }
}
