package ru.any_test.anytest.repo.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TestJson(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title : String,
    @Json(name = "viewCount")
    val viewCount : Float,
    @Json(name = "successCount")
    val successCount : Float
)