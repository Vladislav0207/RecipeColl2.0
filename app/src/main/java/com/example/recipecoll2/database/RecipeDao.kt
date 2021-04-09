package com.example.recipecoll2.database

import androidx.room.*
import com.example.recipecoll2.network.Recipe

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(Recipe  : MutableList<LocalRecipe>)

    @Query("UPDATE recipe SET isFavorite = :isSelected WHERE id = :id")
    suspend fun updateRecipe(id:Int,isSelected:Int)

    @Transaction
    @Query("select id, title, readyInMinutes, servings, image, instructions, isFavorite from recipe")
    suspend fun getAllRecipe() : List<Recipe>

}