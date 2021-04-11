package com.example.recipecoll2.network.api

import com.example.recipecoll2.KEY
import com.example.recipecoll2.network.model.Recipes
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

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

}