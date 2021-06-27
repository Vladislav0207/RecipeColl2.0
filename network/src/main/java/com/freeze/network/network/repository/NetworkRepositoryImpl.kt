package com.freeze.network.network.repository

import android.util.Log
import com.freeze.domain.domain.NetworkRepository
import com.freeze.network.network.api.ApiService
import com.freeze.network.network.api.HOST
import com.freeze.network.network.api.NUMBER
import com.freeze.domain.domain.model.DomainRecipe
import com.freeze.network.network.api.KEY
import com.freeze.network.network.mapper.toDomainRecipe
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
            Log.e("recepies error: ", e.message.toString())
            e.printStackTrace()
            mutableListOf()
        }
    }
}