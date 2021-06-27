package com.freeze.domain.domain

import com.freeze.domain.domain.model.DomainRecipe
import com.freeze.domain.domain.model.IngredientOnlyName

interface RecipeInteractor {
    suspend fun getData(): MutableList<DomainRecipe>

    suspend fun updateRecipe(recipeId: Int, isSelected: Int)

    suspend fun getAllIngredientsOnlyName(): MutableSet<IngredientOnlyName>

    suspend fun searchByIngredient(listOfNames: MutableList<IngredientOnlyName>): MutableList<DomainRecipe>

    suspend fun getFavorites(): MutableList<DomainRecipe>

    suspend fun getRecipeById(id: Int): DomainRecipe
}