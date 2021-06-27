package com.freeze.network.network.mapper

import com.freeze.domain.domain.model.DomainIngredient
import com.freeze.domain.domain.model.DomainRecipe
import com.freeze.network.network.model.ResponseIngredient
import com.freeze.network.network.model.ResponseRecipe

fun ResponseIngredient.toDomainIngredient() : DomainIngredient =
    DomainIngredient(
        id,
        image,
        nameClean,
        amount,
        unit,
        recipe_id,
        key,
        isSelect
    )
fun ResponseRecipe.toDomainRecipe() : DomainRecipe =
    DomainRecipe(
        extendedIngredients.mapTo(mutableListOf()) {
            it.toDomainIngredient()
        },
        id,
        title,
        readyInMinutes,
        servings,
        image,
        instructions,
        isFavorite
    )