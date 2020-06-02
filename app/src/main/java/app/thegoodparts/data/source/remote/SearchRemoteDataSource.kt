package app.thegoodparts.data.source.remote

import app.thegoodparts.BuildConfig
import app.thegoodparts.data.source.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

private const val ENDPOINT_GET_ADDRESS = "compassLocation/rest/address/autocomplete"

interface SearchRemoteDataSource {
    /**
     * Fetches lists of address based on the [queryString] passed by the user.
     *
     * https://digi-api.airtel.in/compassLocation/rest/address/autocomplete?queryString=airtel&city=gurgaon
     * @param city address associated to city
     * @param queryString address text to be searched
     */
    @GET
    suspend fun getAddressList(
        @Url url: String = BuildConfig.SEARCH_API_BASE_URL + ENDPOINT_GET_ADDRESS,
        @Query("queryString") queryString: String,
        @Query("city") city: String = "gurgaon"
    ): SearchResponse
}