package com.gourav.androidlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import com.gourav.androidlibrary.api.APIService
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

            //start a coroutine scope
            runBlocking(Dispatchers.IO) {
                /*getting the retrofit instance from singleton and calling API*/
                val callAPI = RetroInstance.getRetroInstance()
                    .create(APIService::class.java)
                    .getHeadlines("us", "business", "ba405e756b0a4141948317e8b122f77")

                //getting response in a variable
                val response: ResponseModel = BuildRetrofit.getResponse(callAPI)

                //check response status if true or false
                if (response.status) {
                    //itf true - use convertResponse method to get response in your own data class object (here is NewsModel)
                    val newsModel: NewsModel = BuildRetrofit.convertResponse(
                        response.Data.toString(),
                        NewsModel::class.java
                    )
                    Log.d(TAG, "onCreate: nm: ${newsModel.totalResults}")
                    runOnUiThread {
                        //do UI related work
                    }
                } else {
                    //if false - get error message basis on the error code
                    Log.e(TAG, "onCreate: error message: ${response.message}")
                }
            }
        }
    }
}