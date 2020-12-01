package ru.any_test.anytest.repo.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnswerJson(
    @Json(name = "answer")
    val answer: String,
    @Json(name = "correct")
    val correct: Boolean
)
