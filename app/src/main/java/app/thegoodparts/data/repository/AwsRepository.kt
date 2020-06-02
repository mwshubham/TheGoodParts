package app.thegoodparts.data.repository

import app.thegoodparts.BuildConfig
import app.thegoodparts.data.source.remote.AwsRemoteDataSource
import app.thegoodparts.data.source.remote.response.BaseResponse
import app.thegoodparts.extensions.toMD5

class AwsRepository(private val service: AwsRemoteDataSource) {

    suspend fun getTestData(offset: Int = 0, limit: Int = 0): BaseResponse<String> {
        val timestamp = System.currentTimeMillis().toString()
        return service.getTestData(
            apiKey = BuildConfig.API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timestamp = timestamp,
            offset = offset,
            limit = limit
        )
    }

    // ============================================================================================
    //  Private generators methods
    // ============================================================================================

    /**
     * Generate a md5 digest of the timestamp parameter, private API key and public.
     *
     * @param timestamp A digital current record of the time.
     * @return The MD5 Hash
     */
    private fun generateApiHash(timestamp: String) =
        BuildConfig.HASH_FORMAT.format(timestamp, BuildConfig.API_PRIVATE_KEY, BuildConfig.API_PUBLIC_KEY).toMD5()

}