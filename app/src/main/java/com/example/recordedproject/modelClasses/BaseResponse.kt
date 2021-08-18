package com.example.recordedproject.modelClasses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse <T>(

    @Json(name = "data")
    val data:T? ,

    @Json(name = "success")
    val success :Boolean,

    @Json(name = "message")
    val message:String

        )