package ru.any_test.anytest.repo.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TestResponse(
    @Json(name = "questions")
    val questions: List<QuestionJson>,
    @Json(name = "comments")
    val comments: List<CommentJson>
)