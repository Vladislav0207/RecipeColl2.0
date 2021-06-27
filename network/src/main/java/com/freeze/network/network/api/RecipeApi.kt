package com.freeze.network.network.api

import com.freeze.network.network.model.Recipes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val KEY ="11ce03dfb8msh1b2f22da42cea2fp153590jsnaaae33be1acd"
const val BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"
const val HOST ="spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"
const val NUMBER = 1


interface ApiService {

    @GET("random")
    suspend fun getRecipes(
        @Header("x-rapidapi-key") key: String = KEY,
        @Header("x-rapidapi-host") host: String = HOST,
        @Query("number") number: Int = NUMBER
    ): Recipes

    companion object Factory {
        fun create(): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}