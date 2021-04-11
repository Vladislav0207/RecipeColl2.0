package com.example.recipecoll2.database.model

import androidx.room.*

data class Recipe(
    @Relation(parentColumn = "id", entityColumn = "recipe_id")
    var extendedIngredients: List<Ingredient>,
    var id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings: Int,
    val image: String,
    val instructions: String,
    var isFavorite: Int = 0
)


@Entity(tableName = "ingredient")
data class Ingredient(
    var id: Int,
    var image: String,
    var nameClean: String,
    var amount: String,
    var unit: String,
    var recipe_id: Int,
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0,
    var isSelect: Int = 0
)