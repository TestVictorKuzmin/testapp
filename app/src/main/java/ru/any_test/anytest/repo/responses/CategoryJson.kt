package ru.any_test.anytest.repo.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryJson(
    @Json(name = "id")
    val categoryId: Int,
    @Json(name = "title")
    val title : String,
    @Json(name = "image")
    val image : String,
    @Json(name = "testsCount")
    val testsCount: Int
)
