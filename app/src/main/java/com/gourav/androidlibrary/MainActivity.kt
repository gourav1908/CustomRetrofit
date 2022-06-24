package com.gourav.androidlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gourav.retrofitlib.BuildRetrofit
import com.gourav.retrofitlib.DemoAPIService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // we are not using Original Retrofit Lib in this project Gradle
        // instead we are using our own created Lib using Retrofit
        val createRetrofit = BuildRetrofit.getRetrofitInstance("https://baseurl.com/")
            .create(DemoAPIService::class.java)
            .getAppointmentDetails("email@gmail.com", "password123")
    }
}