package com.example.recipecoll2.domain

import com.example.recipecoll2.domain.model.DomainRecipe
import com.example.recipecoll2.domain.model.IngredientOnlyName

interface RecipeInteractor {
    suspend fun getData(): MutableList<DomainRecipe>

    suspend fun updateRecipe(recipeId: Int, isSelected: Int)

    suspend fun getAllIngredientsForView(): MutableSet<IngredientOnlyName>

    suspend fun searchByIngredient(listOfNames: MutableList<IngredientOnlyName>): MutableList<DomainRecipe>

    suspend fun getFavorites(): MutableList<DomainRecipe>

    suspend fun getRecipeById(id: Int): DomainRecipe
}