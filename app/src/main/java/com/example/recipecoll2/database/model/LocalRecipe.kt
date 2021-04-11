package com.example.recipecoll2.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recipe")
data class LocalRecipe(
    @PrimaryKey val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings: Int,
    val image: String,
    val instructions: String,
    var isFavorite: Int
)