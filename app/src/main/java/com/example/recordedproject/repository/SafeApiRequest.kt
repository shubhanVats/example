package com.example.recordedproject.repository

import com.example.recordedproject.utils.NoInternet
import javax.inject.Inject

class SafeApiRequest  @Inject constructor(private val networkHelper: NetworkHelper){
    suspend fun <T: Any> apiRequest(dataRequest: suspend () -> T) : DataState<T> {
        return try {
            if (networkHelper.isNetworkConnected()){
                DataState.Success(dataRequest.invoke())
            }
            else{
                throw NoInternet("Please check internet")
            }

        }
        catch (throwable : Throwable){
            throw NoInternet("Please check internet")
        }


    }

}