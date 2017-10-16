
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kevinmargueritte/Documents/graphQL/conf/routes
// @DATE:Mon Oct 16 08:35:57 CEST 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseGetBestCommitersController GetBestCommitersController = new controllers.ReverseGetBestCommitersController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseTopLanguagesController TopLanguagesController = new controllers.ReverseTopLanguagesController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseGetStatsIssuesController GetStatsIssuesController = new controllers.ReverseGetStatsIssuesController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseGetBestCommitersController GetBestCommitersController = new controllers.javascript.ReverseGetBestCommitersController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseTopLanguagesController TopLanguagesController = new controllers.javascript.ReverseTopLanguagesController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseGetStatsIssuesController GetStatsIssuesController = new controllers.javascript.ReverseGetStatsIssuesController(RoutesPrefix.byNamePrefix());
  }

}
