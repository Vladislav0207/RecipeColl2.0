package com.example.recipecoll2.database.mapper

import com.example.recipecoll2.database.model.Ingredient
import com.example.recipecoll2.database.model.LocalRecipe
import com.example.recipecoll2.database.model.Recipe
import com.example.recipecoll2.domain.model.DomainIngredient
import com.example.recipecoll2.domain.model.DomainRecipe


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