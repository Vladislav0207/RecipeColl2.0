package com.example.recipecoll2.database.repository

import android.content.Context
import androidx.room.Room
import com.example.recipecoll2.database.RecipeDatabase
import com.example.recipecoll2.database.mapper.toDomainIngredient
import com.example.recipecoll2.database.mapper.toDomainRecipe
import com.example.recipecoll2.database.mapper.toIngredient
import com.example.recipecoll2.database.mapper.toLocalRecipe
import com.example.recipecoll2.database.model.LocalRecipe
import com.example.recipecoll2.domain.DatabaseRepository
import com.example.recipecoll2.database.model.Ingredient
import com.example.recipecoll2.database.model.Recipe
import com.example.recipecoll2.domain.model.DomainIngredient
import com.example.recipecoll2.domain.model.DomainRecipe
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(@ApplicationContext context: Context) :
    DatabaseRepository {
    val recipeDatabase: RecipeDatabase = Room.databaseBuilder(
        context,
        RecipeDatabase::class.java,
        "recipe_db"
    )
        .build()

    override suspend fun insertRecipes(domainRecipes: MutableList<DomainRecipe>) {
        val localRecipes = domainRecipes.mapTo(mutableListOf()) {
            it.toLocalRecipe()
        }
        recipeDatabase.recipeDao().insertRecipe(localRecipes)
    }

    override suspend fun getAllRecipes(): MutableList<DomainRecipe> {
        return recipeDatabase.recipeDao().getAllRecipe().mapTo(mutableListOf()) {
            it.toDomainRecipe()
        }
    }

    override suspend fun insertIngredients(domainIngredients: MutableList<DomainIngredient>) {
        val localIngredients = domainIngredients.mapTo(mutableListOf()) {
            it.toIngredient()
        }.toList()
        recipeDatabase.ingredientDao().insertAllIngredients(localIngredients)
    }

    override suspend fun updateRecipe(id: Int, isSelected: Int) {
        recipeDatabase.recipeDao().updateRecipe(id, isSelected)
    }

    override suspend fun getAllIngredients(): MutableList<DomainIngredient> {
        return recipeDatabase.ingredientDao().getAllIngredients().mapTo(mutableListOf()) {
            it.toDomainIngredient()
        }
    }

    override suspend fun getRecipeById(id: Int): DomainRecipe {
        return recipeDatabase.recipeDao().getRecipeById(id).toDomainRecipe()
    }

}