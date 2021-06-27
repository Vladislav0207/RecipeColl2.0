package com.freeze.database.database.mapper

import com.freeze.database.database.model.Ingredient
import com.freeze.database.database.model.LocalRecipe
import com.freeze.database.database.model.Recipe
import com.freeze.domain.domain.model.DomainIngredient
import com.freeze.domain.domain.model.DomainRecipe


fun Ingredient.toDomainIngredient(): DomainIngredient =
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

fun DomainIngredient.toIngredient(): Ingredient =
    Ingredient(
        id,
        image,
        nameClean,
        amount,
        unit,
        recipe_id,
        key,
        isSelect
    )

fun Recipe.toDomainRecipe(): DomainRecipe =
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

fun DomainRecipe.toLocalRecipe(): LocalRecipe =
    LocalRecipe(
        id,
        title,
        readyInMinutes,
        servings,
        image,
        instructions,
        isFavorite
    )