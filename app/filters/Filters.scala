package filters

import javax.inject.Inject

import play.api.http.DefaultHttpFilters
import play.filters.cors.CORSFilter

/**
  * Created by kevinmargueritte on 16/10/2017.
  */
class Filters @Inject() (corsFilter: CORSFilter)
  extends DefaultHttpFilters(corsFilter)
