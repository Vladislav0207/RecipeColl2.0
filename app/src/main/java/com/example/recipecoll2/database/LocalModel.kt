package com.example.recipecoll2.database

import android.content.Context
import androidx.room.Room
import com.example.recipecoll2.database.model.LocalRecipe
import com.example.recipecoll2.network.Ingredient
import com.example.recipecoll2.network.Recipe
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalModel @Inject constructor (@ApplicationContext context: Context) {
    //build database
    val recipeDatabase : RecipeDatabase = Room.databaseBuilder(context,
        RecipeDatabase::class.java,
        "recipe_db")
        .build()

//realization DaoFun
    suspend fun insertRecipes(localRecipes : MutableList<LocalRecipe>){
        recipeDatabase.recipeDao().insertRecipe(localRecipes)
    }

    suspend fun getAllRecipes(): List<Recipe>{
        return  recipeDatabase.recipeDao().getAllRecipe()
    }

    suspend fun insertIngredients(localIngredients : List<Ingredient>){
        recipeDatabase.ingredientDao().insertAllIngredients(localIngredients)
    }

    suspend fun updateRecipe(id:Int, isSelected:Int){
        recipeDatabase.recipeDao().updateRecipe(id, isSelected)
    }

    suspend fun getAllIngredients(): List<Ingredient>{
       return recipeDatabase.ingredientDao().getAllIngredients()
    }

}