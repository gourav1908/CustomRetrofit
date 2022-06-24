package com.gourav.retrofitlib

import retrofit2.Response
import retrofit2.http.*

interface DemoAPIService {
    @GET("userProfile")
    fun getAppointmentDetails(
        @Query("email") email: String,
        @Query("password") passwrod: String
    ): Response<DemoModel>
}