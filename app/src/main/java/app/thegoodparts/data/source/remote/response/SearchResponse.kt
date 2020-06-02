package app.thegoodparts.data.source.remote.response

/**
 * Search network response.
 *
 * @param requestId
 * @param data The results returned by the call.
 */
data class SearchResponse(
    val requestId: String,
    val data: SearchData
)

/**
 * API data network response format.
 *
 * @param autoCompleteRequestString
 * @param focusWord
 * @param addressList The list of [Address] returned by the call.
 */
data class SearchData(
    val autoCompleteRequestString: String,
    val focusWord: String,
    val addressList: List<Address>
)

/**
 * Address data
 *
 * @param id
 * @param addressString
 */
data class Address(
    val id: String,
    val addressString: String
)
