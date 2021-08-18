package com.example.recordedproject.repository

import com.example.recordedproject.modelClasses.BaseResponse
import com.example.recordedproject.modelClasses.ColorResponse
import com.example.recordedproject.utils.Constants.ColorSearchEndPoint
import retrofit2.http.GET

interface ApiService {
    @GET(ColorSearchEndPoint)
    suspend fun getColors(): BaseResponse<ArrayList<ColorResponse>>


}