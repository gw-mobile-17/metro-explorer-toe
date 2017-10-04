package com.yubo.han.toe

/**
 * Created by han on 9/26/17.
 */
object Constants {
    // Yelp search
    val YELP_SEARCH_TERM = "landmarks"
    val YELP_SEARCH_URL = "https://api.yelp.com/v3/businesses/search"
    val YELP_SEARCH_TOKEN = "Bearer 7V8pfQGeJaRcSJHwizHuMJ_OMHuITa4DrO7ZXpTDCgv1CclbSkctHq0qtMi-jG5PkxIQ8ZoZuy_G0v338DFGw9qZMHNf4TQmugebOLIukFW8lL1uJTRzcySOMkTHWXYx"
    val YELP_JSON_MEMBER_NAME = "businesses"
    val YELP_SEARCH_RADIUS = 2500
    // Yelp search near metro station
    val YELP_SEARCH_NEAR_METRO_TERM = "Metro Stations"
    val YELP_SEARCH_NEAR_METRO_RADIUS = 40000
    val YELP_SEARCH_NEAR_METRO_SORT = "sort_by"
    val YELP_SEARCH_NEAR_METRO_SORT_BY = "distance"

    //WMATA search API
    val METRO_ALLSTATION_SEARCH_URL = "https://api.wmata.com/Rail.svc/json/jStations?oauth_signature_method=HMAC-SHA1&oauth_timestamp=1506294098&oauth_nonce=V41Ok3&oauth_version=1.0&oauth_signature=KCb50NS6o9prToEAYXK/l+MtMZY=&api_key=107c9be7cf36407f9ff94b63e93b0b6d"
    val METRO_JSON_MEMBER_NAME = "Stations"
    val LANDMARKS_PREF_KEY = "LANDMARKS"
}