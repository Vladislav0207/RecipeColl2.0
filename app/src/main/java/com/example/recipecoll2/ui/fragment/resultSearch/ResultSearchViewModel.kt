package com.example.recipecoll2.ui.fragment.resultSearch

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ResultSearchViewModel @ViewModelInject constructor(
    private val recipeInteractor: RecipeInteractor
) : ViewModel() {


    fun updateInFavorites(recipeId: Int) {
        viewModelScope.launch {
            recipeInteractor.updateRecipe(recipeId, 1)
        }
    }

    fun updateOutFavorites(recipeId: Int) {
        viewModelScope.launch {
            recipeInteractor.updateRecipe(recipeId, 0)
        }
    }
}