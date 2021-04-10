package com.example.recipecoll2.network.mapper

import com.example.recipecoll2.domain.model.DomainIngredient
import com.example.recipecoll2.domain.model.DomainRecipe
import com.example.recipecoll2.network.model.ResponseIngredient
import com.example.recipecoll2.network.model.ResponseRecipe

fun ResponseIngredient.toDomainIngredient() : DomainIngredient =
    DomainIngredient(
        id,
        image,
        nameClean,
        amount,
        unit,
        recipe_id,
        key,
        isSelect)
fun ResponseRecipe.toDomainRecipe() : DomainRecipe =
    DomainRecipe(
        extendedIngredients.mapTo(mutableListOf()){
        it.toDomainIngredient()
    },
        id,
        title,
        readyInMinutes,
        servings,
        image,
        instructions,
        isFavorite)