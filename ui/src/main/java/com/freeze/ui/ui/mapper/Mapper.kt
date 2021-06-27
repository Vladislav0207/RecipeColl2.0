package com.freeze.ui.ui.mapper

import com.freeze.domain.domain.model.DomainIngredient
import com.freeze.domain.domain.model.DomainRecipe
import com.freeze.domain.domain.model.IngredientOnlyName
import com.freeze.ui.ui.model.IngredientOnlyNameView
import com.freeze.ui.ui.model.IngredientView
import com.freeze.ui.ui.model.RecipeView

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

fun IngredientOnlyName.toIngredientOnlyNameView() =
    IngredientOnlyNameView(
        name,
        isSelect
    )

fun IngredientOnlyNameView.toIngredientOnlyName() =
    IngredientOnlyName(
        name,
        isSelect
    )