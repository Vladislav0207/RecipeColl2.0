package com.example.recipecoll2.domain

import com.example.recipecoll2.domain.model.DomainIngredient
import com.example.recipecoll2.domain.model.DomainRecipe


interface DatabaseRepository {

    suspend fun insertRecipes(domainRecipes : MutableList<DomainRecipe>)

    suspend fun getAllRecipes(): MutableList<DomainRecipe>

    suspend fun insertIngredients(domainIngredients : MutableList<DomainIngredient>)

    suspend fun updateRecipe(id:Int, isSelected:Int)

    suspend fun getAllIngredients(): MutableList<DomainIngredient>
}