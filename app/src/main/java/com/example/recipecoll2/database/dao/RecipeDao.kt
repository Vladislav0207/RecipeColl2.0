package com.example.recipecoll2.database.dao

import androidx.room.*
import com.example.recipecoll2.database.model.LocalRecipe
import com.example.recipecoll2.database.model.Recipe

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(Recipe: MutableList<LocalRecipe>)

    @Query("UPDATE recipe SET isFavorite = :isSelected WHERE id = :id")
    suspend fun updateRecipe(id: Int, isSelected: Int)

    @Transaction
    @Query("select id, title, readyInMinutes, servings, image, instructions, isFavorite from recipe")
    suspend fun getAllRecipe(): List<Recipe>

    @Transaction
    @Query("select id, title, readyInMinutes, servings, image, instructions, isFavorite from recipe where id = :id")
    suspend fun getRecipeById(id: Int): Recipe

}