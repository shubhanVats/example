package com.example.recordedproject.modelClasses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ColorResponse(
    @Json(name = "apiUrl")
    val apiUrl: String,
    @Json(name = "badgeUrl")
    val badgeUrl: String,
    @Json(name = "dateCreated")
    val dateCreated: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "hex")
    val hex: String,
    @Json(name = "hsv")
    val hsv: Hsv,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imageUrl")
    val imageUrl: String,
    @Json(name = "numComments")
    val numComments: Int,
    @Json(name = "numHearts")
    val numHearts: Int,
    @Json(name = "numViews")
    val numViews: Int,
    @Json(name = "numVotes")
    val numVotes: Int,
    @Json(name = "rank")
    val rank: Int,
    @Json(name = "rgb")
    val rgb: Rgb,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "userName")
    val userName: String
)

@JsonClass(generateAdapter = true)
data class Hsv(
    @Json(name = "hue")
    val hue: Int,
    @Json(name = "saturation")
    val saturation: Int,
    @Json(name = "value")
    val value: Int
)

@JsonClass(generateAdapter = true)
data class Rgb(
    @Json(name = "blue")
    val blue: Int,
    @Json(name = "green")
    val green: Int,
    @Json(name = "red")
    val red: Int
)