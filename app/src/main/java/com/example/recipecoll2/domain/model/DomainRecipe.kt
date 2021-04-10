package com.example.recipecoll2.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

data class DomainRecipe (
    var extendedIngredients: List<DomainIngredient>,
    var id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings:Int,
    val image: String,
    val instructions: String,
    var isFavorite : Int = 0
)

data class DomainIngredient(
    var id: Int,
    var image:String,
    var nameClean: String,
    var amount: String,
    var unit: String,
    var recipe_id: Int,
    var key : Int=0,
    var isSelect : Int =0
)