package app.thegoodparts.data.source.remote.response

/**
 * Generic network response for any type data [T].
 *
 * @param code The HTTP status code of the returned result.
 * @param status A string description of the call status.
 * @param message A more descriptive message of the failure call status.
 * @param data The results returned by the call.
 */
data class BaseResponse<T>(
    val code: Any,
    val status: String,
    val message: String?,
    val data: DataResponse<T>
)