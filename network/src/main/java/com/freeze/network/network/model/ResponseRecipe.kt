package com.freeze.network.network.model

import com.google.gson.annotations.SerializedName


data class Recipes(
    @SerializedName("recipes")
    val recipes: MutableList<ResponseRecipe>
)

data class  ResponseRecipe (
    @SerializedName("extendedIngredients")
    var extendedIngredients: List<ResponseIngredient>,
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,
    @SerializedName("servings")
    val servings:Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("instructions")
    val instructions: String,
    var isFavorite : Int = 0
)
data class ResponseIngredient(
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image:String,
    @SerializedName("nameClean")
    var nameClean: String,
    @SerializedName("amount")
    var amount: String,
    @SerializedName("unit")
    var unit: String,
    @SerializedName("recipe_id")
    var recipe_id: Int,
    var key : Int=0,
    var isSelect : Int =0
)