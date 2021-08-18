package com.example.recordedproject.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(@Json(name = "success")
val  success : Boolean,
                         @Json(name = "message")
                         val  message : Boolean,
)