package app.thegoodparts.data.source.remote

import app.thegoodparts.data.source.remote.response.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AwsRemoteDataSource {

    @GET("TestLambda")
    suspend fun getTestData(
        @Query("apiKey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): BaseResponse<String>
}