package ru.any_test.anytest.repo.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class QuestionJson(
    @Json(name = "type")
    val type: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "answers")
    val answers: List<AnswerJson>
)