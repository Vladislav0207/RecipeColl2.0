package com.freeze.domain.domain.model

data class DomainRecipe(
    var extendedIngredients: List<DomainIngredient>,
    var id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings: Int,
    val image: String,
    val instructions: String,
    var isFavorite: Int = 0
)

