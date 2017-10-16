
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kevinmargueritte/Documents/graphQL/conf/routes
// @DATE:Mon Oct 16 08:35:57 CEST 2017

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers {

  // @LINE:7
  class ReverseGetBestCommitersController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def getBestCommiters(owner:String, repository:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "commiters/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("owner", owner)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("repository", repository)))
    }
  
  }

  // @LINE:10
  class ReverseTopLanguagesController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def topLanguages(user:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "user/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("user", user)) + "/languages")
    }
  
  }

  // @LINE:16
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def at(): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"), ("file", "index.html"))); _rrc
      Call("GET", _prefix)
    }
  
    // @LINE:16
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:13
  class ReverseGetStatsIssuesController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def getStatsIssues(owner:String, repository:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "issues/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("owner", owner)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("repository", repository)))
    }
  
  }


}
