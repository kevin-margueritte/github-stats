
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kevinmargueritte/Documents/graphQL/conf/routes
// @DATE:Mon Oct 16 08:35:57 CEST 2017

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers.javascript {

  // @LINE:7
  class ReverseGetBestCommitersController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def getBestCommiters: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.GetBestCommitersController.getBestCommiters",
      """
        function(owner0,repository1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "commiters/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("owner", owner0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("repository", repository1))})
        }
      """
    )
  
  }

  // @LINE:10
  class ReverseTopLanguagesController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def topLanguages: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.TopLanguagesController.topLanguages",
      """
        function(user0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "user/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("user", user0)) + "/languages"})
        }
      """
    )
  
  }

  // @LINE:16
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:16
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:13
  class ReverseGetStatsIssuesController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def getStatsIssues: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.GetStatsIssuesController.getStatsIssues",
      """
        function(owner0,repository1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "issues/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("owner", owner0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("repository", repository1))})
        }
      """
    )
  
  }


}
