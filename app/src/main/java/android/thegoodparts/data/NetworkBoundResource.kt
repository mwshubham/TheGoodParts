package android.thegoodparts.data

import android.content.Context
import android.thegoodparts.ApiState
import android.thegoodparts.constants.ErrorType
import android.thegoodparts.utilities.CoreNetworkUtils
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response
import timber.log.Timber

/**
 * A generic class that can provide a resource backed by both
 * the SQLite database and the network.
 *
 * [ResultType] represents the type for database.
 * [RequestType] represents the type for network.
 */
abstract class NetworkBoundResource<ResultType, RequestType> {

    @ExperimentalCoroutinesApi
    fun asFlow(appContext: Context) = flow {
        emit(ApiState.loading())
        val dbValue = loadFromDb().first()
        if (shouldFetch(dbValue)) {
            emit(ApiState.success(dbValue))
            try {
                val apiResponse = fetchFromNetwork()
                when {
                    apiResponse.isSuccessful && apiResponse.body() != null -> {
                        apiResponse.body()?.let { saveNetworkResult(it) }
                        emitAll(loadFromDb().map { ApiState.success(it) })
                    }
                    else -> {
                        emit(ApiState.error(ErrorType.UnknownError))
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                if (CoreNetworkUtils.isInternetAvailable(appContext)) {
                    emit(ApiState.error(ErrorType.UnknownError))
                } else {
                    emit(ApiState.error(ErrorType.NetworkError.InternetUnavailable))
                }
            }
        } else {
            emitAll(loadFromDb().map { ApiState.success(it) })
        }
    }

    @WorkerThread
    protected open fun processResponse(response: Response<RequestType>) = response

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(response: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): Response<RequestType>
}