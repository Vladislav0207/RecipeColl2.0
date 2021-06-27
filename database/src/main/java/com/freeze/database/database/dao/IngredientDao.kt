package com.freeze.database.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.freeze.database.database.model.Ingredient

@Dao
interface IngredientDao {
    @Insert
    suspend fun insertAllIngredients(localIngredients: List<Ingredient>)

    @Query("SELECT * FROM ingredient")
    suspend fun getAllIngredients(): List<Ingredient>

}