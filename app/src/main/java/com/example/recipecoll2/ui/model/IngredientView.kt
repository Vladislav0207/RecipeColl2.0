package com.example.recipecoll2.ui.model

data class IngredientView(
    var id: Int,
    var image: String,
    var nameClean: String,
    var amount: String,
    var unit: String,
    var recipe_id: Int,
    var key: Int = 0,
    var isSelect: Int = 0
)