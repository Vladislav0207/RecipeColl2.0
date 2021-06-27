package com.freeze.domain.domain

import com.freeze.domain.domain.model.DomainIngredient
import com.freeze.domain.domain.model.DomainRecipe


interface DatabaseRepository {

    suspend fun insertRecipes(domainRecipes: MutableList<DomainRecipe>)

    suspend fun getAllRecipes(): MutableList<DomainRecipe>

    suspend fun insertIngredients(domainIngredients: MutableList<DomainIngredient>)

    suspend fun updateRecipe(id: Int, isSelected: Int)

    suspend fun getAllIngredients(): MutableList<DomainIngredient>

    suspend fun getRecipeById(id: Int): DomainRecipe
}