package com.example.recipecoll2.network

import com.example.recipecoll2.KEY
import java.lang.Exception
import javax.inject.Inject

class RemoteModel @Inject constructor() {
    private val apiService = ApiService.create()
    suspend fun getRemoteDataRecipe() : MutableList<Recipe>{
        return try {
            val  recipesList = apiService.getRecipes(KEY, HOST, NUMBER).recipes
            for (i in 0 until NUMBER){
                recipesList[i].extendedIngredients.forEach{it.recipe_id = recipesList[i].id }
            }

            recipesList
        }
        catch (e : Exception){
            mutableListOf()
        }
    }
}