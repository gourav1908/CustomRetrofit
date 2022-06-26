package com.gourav.androidlibrary

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gourav.androidlibrary.api.APIService
import com.gourav.androidlibrary.models.PostModel
import com.gourav.retrofitlib.BuildRetrofit
import com.gourav.retrofitlib.model.ResponseModel
import com.gourav.uselibrary.models.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    private val TAG: String? = "main>>>>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_click = findViewById<Button>(R.id.btn_click)
        btn_click.setOnClickListener {
            // Change BASE_URL is RetroInstance object as per the APIs below before using
            getHeadlines() // URL = BASE_URL
            getPosts() // URL = BASE_URL2
        }
    }

    /*response in JSONObject*/
    private fun getHeadlines() {
        /*start a coroutine scope*/
        runBlocking(Dispatchers.IO) {
            /*getting the retrofit instance from singleton and calling API*/
            val apiCall = RetroInstance.getRetroInstance()
                .create(APIService::class.java)
                .getHeadlines("us", "business", "ba405e756b0a4141948317e8b122f777")

            /*getting response in a variable*/
            val response: ResponseModel = BuildRetrofit.getResponse(apiCall)

            /*check response status if true or false*/
            if (response.status) {
                /*if true - use convertResponse method to get response in your own data class object (here is NewsModel)*/
                /*Note: here using **convertToObject** method to get data in NewsModel object format*/
                val newsModel: NewsModel = BuildRetrofit.convertToObject(
                    response.Data.toString(),
                    NewsModel::class.java
                )
                Log.d(TAG, "onCreate: nm: ${newsModel.totalResults}")
                runOnUiThread {
                    /*do UI related stuff*/
                }
            } else {
                /*if false - Show error message as per response code*/
                response.code
                Log.e(TAG, "onCreate: error message: ${response.message}")
            }
        }
    }

    /*response in JSONArray*/
    private fun getPosts() {
        /*start a coroutine scope*/
        runBlocking {
            /*getting the retrofit instance from singleton and calling API*/
            val apiCall = RetroInstance.getRetroInstance()
                .create(APIService::class.java)
                .getPosts()

            /*getting response in a variable*/
            val response = BuildRetrofit.getResponse(apiCall)

            /*check response status if true or false*/
            if (response.status) {
                /*if true - use convertResponse method to get response in your own data class object (here is PostModel)*/
                /*Note: here using **convertToList** method to get data in List<PostModel> format*/
                val postList =
                    BuildRetrofit.convertToList(
                        response.Data.toString(),
                        listOf<PostModel>()::class.java
                    )
                Log.d(TAG, "onCreate: size ab ${postList.size}")
                runOnUiThread {
                    /*do UI related stuff*/
                }
            } else {
                /*if false - Show error message as per response code*/
                response.code
                Log.e(TAG, "onCreate: error occured: ${response.message}")
            }
        }
    }
}