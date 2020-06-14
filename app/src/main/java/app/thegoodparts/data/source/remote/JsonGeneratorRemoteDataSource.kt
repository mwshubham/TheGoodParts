package app.thegoodparts.data.source.remote

import app.thegoodparts.BuildConfig
import app.thegoodparts.data.source.remote.response.Company
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

private const val ENDPOINT_GET_CLUB_INFO = "api/json/get/Vk-LhK44U"

interface JsonGeneratorRemoteDataSource {
    /**
     * Retrieves a list of companies and its member
     */
    @GET
    suspend fun getCompanies(
        @Url url: String = BuildConfig.CLUB_API_BASE_URL + ENDPOINT_GET_CLUB_INFO
    ): Response<List<Company>>
}

