package com.gourav.retrofitlib

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BuildRetrofit {

    fun getRetrofitInstance(BASE_URL: String): Retrofit {
        val retroClient = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retroClient
    }
}