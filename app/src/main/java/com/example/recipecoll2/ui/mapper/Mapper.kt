package com.example.recipecoll2.ui.mapper

import com.example.recipecoll2.domain.model.DomainIngredient
import com.example.recipecoll2.domain.model.DomainRecipe
import com.example.recipecoll2.ui.model.IngredientView
import com.example.recipecoll2.ui.model.RecipeView

fun DomainIngredient.toIngredientView() =
    IngredientView(
        id,
        image,
        nameClean,
        amount,
        unit,
        recipe_id,
        key,
        isSelect
    )

fun DomainRecipe.toRecipeView() =
    RecipeView(
        extendedIngredients.mapTo(mutableListOf()) {
            it.toIngredientView()
        },
        id,
        title,
        readyInMinutes,
        servings,
        image,
        instructions,
        isFavorite
    )