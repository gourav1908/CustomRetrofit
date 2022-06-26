package com.gourav.androidlibrary.api

import com.google.gson.JsonElement
import com.gourav.retrofitlib.model.ResponseModel
import com.gourav.uselibrary.models.NewsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("userProfile")
    fun getAppointmentDetails(
        @Query("email") email: String,
        @Query("password") passwrod: String
    ): ResponseModel

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Response<JsonElement>

    @GET("posts")
    suspend fun getPosts(): Response<JsonElement>
}