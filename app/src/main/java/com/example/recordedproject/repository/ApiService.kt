package com.example.recordedproject.repository

import com.example.recordedproject.ColorResponse.BaseResponse
import com.example.recordedproject.ColorResponse.ColorResponseObject
import com.example.recordedproject.utils.Constants.ColorSearchEndPoint
import retrofit2.http.GET

interface ApiService {
    @GET(ColorSearchEndPoint)
    suspend fun getColors(): BaseResponse<ArrayList<ColorResponseObject>>


}