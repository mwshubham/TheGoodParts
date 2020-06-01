package android.thegoodparts

import android.thegoodparts.constants.ErrorType

/**
 * Describes state of the view at any
 * point of time.
 */
sealed class ApiState<ResultType> {

    /**
     * Describes loading state of the UI
     */
    class Loading<ResultType> : ApiState<ResultType>() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int = javaClass.hashCode()
    }

    /**
     * Describes success state of the UI with
     * [data] shown
     */
    data class Success<ResultType>(
        val data: ResultType
    ) : ApiState<ResultType>()

    /**
     *  Describes error state of the UI
     */
    data class Error<ResultType>(
        val errorType: ErrorType
    ) : ApiState<ResultType>()

    override fun toString(): String {
        if (isLoading()) return "Loading"
        if (isSuccess()) return "Success"
        if (isError()) return "Error"
        return ""
    }

    /**
     * Check if current view state is [Loading].
     *
     * @return True if view is in loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [Success].
     *
     * @return True if view is in success state, otherwise false.
     */
    fun isSuccess() = this is Success

    /**
     * Check if current view state is [Error].
     *
     * @return True if view is in error state, otherwise false.
     */
    fun isError() = this is Error

    companion object {

        /**
         * Creates [ApiState] object with [Loading] state to notify
         * the UI to showing loading.
         */
        fun <ResultType> loading(): ApiState<ResultType> = Loading()

        /**
         * Creates [ApiState] object with [Success] state and [data].
         */
        fun <ResultType> success(data: ResultType): ApiState<ResultType> = Success(data)

        /**
         * Creates [ApiState] object with [Error] state and [ErrorType].
         */
        fun <ResultType> error(errorType: ErrorType): ApiState<ResultType> = Error(errorType)
    }
}