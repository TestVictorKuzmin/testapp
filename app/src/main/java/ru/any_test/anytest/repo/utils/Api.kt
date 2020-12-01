package ru.any_test.anytest.repo.utils

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.any_test.anytest.repo.responses.CategoriesResponse
import ru.any_test.anytest.repo.responses.TestListResponse
import ru.any_test.anytest.repo.responses.TestResponse

interface Api {

    @GET("api/categories")
    fun getCategories(
        @Query("page") type: Int
    ): Single<CategoriesResponse>

    @GET("api/categories/{id}")
    fun getTestList(
        @Path("id") id: Int
    ): Single<TestListResponse>


    @GET("api/tests/{id}")
    fun getTest(
        @Path("id") id: Int
    ): Single<TestResponse>
}