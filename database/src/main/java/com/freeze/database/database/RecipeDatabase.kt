package com.freeze.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.freeze.database.database.dao.IngredientDao
import com.freeze.database.database.model.LocalRecipe
import com.freeze.database.database.dao.RecipeDao
import com.freeze.database.database.model.Ingredient


@Database(entities = [LocalRecipe::class, Ingredient::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
}