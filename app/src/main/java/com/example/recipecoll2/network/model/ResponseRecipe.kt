package com.example.recipecoll2.network.model

import androidx.room.*


data class Recipes(
    val recipes: MutableList<ResponseRecipe>
)

data class  ResponseRecipe (
    var extendedIngredients: List<ResponseIngredient>,
    var id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings:Int,
    val image: String,
    val instructions: String,
    var isFavorite : Int = 0
)
data class ResponseIngredient(
    var id: Int,
    var image:String,
    var nameClean: String,
    var amount: String,
    var unit: String,
    var recipe_id: Int,
    var key : Int=0,
    var isSelect : Int =0
)