package ru.any_test.anytest.repo.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesResponse(
    @Json(name = "totalItems")
    val totalItems: Int,
    @Json(name = "categories")
    val categories: List<CategoryJson>,
    @Json(name = "totalPages")
    val totalPages: Int,
    @Json(name = "currentPage")
    val currentPage: Int
)