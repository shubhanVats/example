package com.example.recordedproject.repository

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val api : ApiService,
    private val safeApiRequest: SafeApiRequest

) {
suspend fun getColorResp() =
    flow {
        emit(DataState.Loading)
        emit(safeApiRequest.apiRequest { api.getColors() })
    }


}