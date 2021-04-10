package com.example.recipecoll2.network.repository

import com.example.recipecoll2.KEY
import com.example.recipecoll2.domain.NetworkRepository
import com.example.recipecoll2.network.api.ApiService
import com.example.recipecoll2.network.api.HOST
import com.example.recipecoll2.network.api.NUMBER
import com.example.recipecoll2.database.model.Recipe
import com.example.recipecoll2.domain.model.DomainRecipe
import com.example.recipecoll2.network.mapper.toDomainRecipe
import java.lang.Exception
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor() : NetworkRepository {
    private val apiService = ApiService.create()
    override suspend fun getRemoteDataRecipe() : MutableList<DomainRecipe> {
        return try {
            val  recipesList = apiService.getRecipes(KEY, HOST, NUMBER).recipes
            for (i in 0 until NUMBER){
                recipesList[i].extendedIngredients.forEach{it.recipe_id = recipesList[i].id }
            }

            recipesList.mapTo(mutableListOf()){
                it.toDomainRecipe()
            }
        }
        catch (e : Exception){
            mutableListOf()
        }
    }
}