package com.example.recordedproject.repository

import com.example.recordedproject.data.ErrorResponse
import com.example.recordedproject.utils.NoInternet
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class SafeApiRequest
@Inject constructor(
    private val networkHelper: NetworkHelper
) {
    suspend fun <T : Any> apiRequest(dataRequest: suspend () -> T): DataState<T> {
        return try {
            if (networkHelper.isNetworkConnected()) {
                DataState.Success(dataRequest.invoke())
            } else
                throw NoInternet("Please check your Internet Connection")
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataState.GenericError(
                    throwable.message,
                    null
                )
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    DataState.GenericError(getErrorMessage(code), errorResponse)
                }
                is SocketTimeoutException -> DataState.NetworkError(
                    throwable,
                    getErrorMessage(ErrorCodes.SocketTimeOut.code)
                )
                is NoInternet -> DataState.NetworkError(
                    throwable,
                    throwable.message!!
                )

                else -> {
                    DataState.GenericError(throwable.message, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}

private fun getErrorMessage(code: Int): String {
    return when (code) {
        ErrorCodes.SocketTimeOut.code -> "Timeout"
        401 -> "Unauthorised"
        404 -> "Not found"
        else -> "Something went wrong"
    }
}