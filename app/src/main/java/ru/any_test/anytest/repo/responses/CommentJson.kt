package ru.any_test.anytest.repo.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentJson(
    @Json(name = "userName")
    val userName: String,
    @Json(name = "time")
    val time: String,
    @Json(name = "text")
    val text : String
)