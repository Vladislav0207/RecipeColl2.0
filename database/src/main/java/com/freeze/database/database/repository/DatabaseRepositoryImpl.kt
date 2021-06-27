package com.freeze.database.database.repository

import android.content.Context
import androidx.room.Room
import com.freeze.database.database.RecipeDatabase
import com.freeze.database.database.mapper.toDomainIngredient
import com.freeze.database.database.mapper.toDomainRecipe
import com.freeze.database.database.mapper.toIngredient
import com.freeze.database.database.mapper.toLocalRecipe
import com.freeze.domain.domain.DatabaseRepository
import com.freeze.domain.domain.model.DomainIngredient
import com.freeze.domain.domain.model.DomainRecipe
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