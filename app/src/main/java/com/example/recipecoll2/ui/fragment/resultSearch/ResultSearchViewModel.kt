package com.example.recipecoll2.ui.fragment.resultSearch

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultSearchViewModel(
    private val context: Context,
    private val recipeInteractor: RecipeInteractor
) : ViewModel() {


    fun updateInFavorites(position: Int) {
        viewModelScope.launch {
            recipeInteractor.updateRecipe(recipeLive.value!![position].id, 1)
            recipeLive.value!![position].isFavorite = 1
            favoriteList.add(recipeLive.value!![position])
        }
    }

    fun updateOutFavorites(recipeId: Int) {
        viewModelScope.launch {
            recipeInteractor.updateRecipe(recipeId, 0)
            favoriteList.removeIf { it.id == recipeId }
        }
    }
}