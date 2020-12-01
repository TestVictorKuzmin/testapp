package ru.any_test.anytest.repo.utils

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Network {
    private val client = OkHttpClient.Builder()
        .build()

    fun create(): Api = Retrofit.Builder()
        .baseUrl("https://anytest-back.herokuapp.com/")
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create()
}