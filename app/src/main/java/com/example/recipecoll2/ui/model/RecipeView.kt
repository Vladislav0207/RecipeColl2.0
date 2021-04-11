package com.example.recipecoll2.ui.model



data class RecipeView (
    var extendedIngredients: List<IngredientView>,
    var id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings:Int,
    val image: String,
    val instructions: String,
    var isFavorite : Int = 0
)