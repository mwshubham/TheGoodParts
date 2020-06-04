package app.thegoodparts.data.repository

import app.thegoodparts.data.source.remote.SearchRemoteDataSource
import app.thegoodparts.data.source.remote.response.SearchResponse
import app.thegoodparts.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@OpenForTesting
@Singleton
open class SearchRepository
@Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource
) {

    suspend fun getAddressList(queryString: String): SearchResponse {
        return searchRemoteDataSource.getAddressList(queryString = queryString)
    }

}