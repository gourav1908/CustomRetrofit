package com.gourav.retrofitlib

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.gourav.retrofitlib.constants.ErrorMessage
import com.gourav.retrofitlib.model.ResponseModel
import org.json.JSONArray
import org.json.JSONTokener
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


object BuildRetrofit {
    fun getRetrofitInstance(BASE_URL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getResponse(response: Response<JsonElement>): ResponseModel {
        when (response.code()) {
            200 -> return ResponseModel(response.code(), true, "", response.body())
            500 -> return ResponseModel(
                response.code(),
                false,
                ErrorMessage.ERROR_500,
                Gson().fromJson(response.errorBody().toString(), JsonElement::class.java)
            )
            404 -> return ResponseModel(
                response.code(),
                false,
                ErrorMessage.ERROR_404,
                Gson().fromJson(response.errorBody().toString(), JsonElement::class.java)
            )
            400, 401, 403 -> return ResponseModel(
                response.code(),
                false,
                ErrorMessage.ERROR_400_401_403,
                Gson().fromJson(response.errorBody().toString(), JsonElement::class.java)
            )
            else -> {
                return ResponseModel(
                    response.code(),
                    false,
                    ErrorMessage.ERROR_UNDEFINED,
                    Gson().fromJson(response.errorBody().toString(), JsonElement::class.java)
                )
            }
        }
    }

    //Use if response is in JSONObject as in {...}
    fun <T : Any> convertToObject(response: String, type: Class<T>): T {
        val json = JSONTokener(response).nextValue()
        return Gson().fromJson(json.toString(), type)
    }

    //Use if response is in JSONArray as in [{...}]
    fun convertToList(response: String, type: Type?): List<Type> {
        val json = JSONTokener(response).nextValue()
        return if (json is JSONArray) {
            Gson().fromJson<List<Type>?>(json.toString(), type).toList()
        } else {
            Gson().fromJson(json.toString(), type)
        }
    }
}