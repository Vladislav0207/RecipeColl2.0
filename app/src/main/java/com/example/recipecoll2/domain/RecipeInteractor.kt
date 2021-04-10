package com.example.recipecoll2.domain

import com.example.recipecoll2.domain.model.DomainRecipe
import com.example.recipecoll2.domain.model.IngredientForView

interface RecipeInteractor {
    suspend fun getData(): MutableList<DomainRecipe>

    suspend fun updateRecipe(recipeId:Int,isSelected:Int)

    suspend fun getAllIngredientsForView(): MutableSet<IngredientForView>

    suspend fun searchByIngredient (listOfNames : MutableList<IngredientForView>) : MutableList<DomainRecipe>

    suspend fun getFavorites():MutableList<DomainRecipe>
}