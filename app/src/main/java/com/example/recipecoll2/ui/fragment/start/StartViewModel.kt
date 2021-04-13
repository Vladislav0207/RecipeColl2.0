package com.example.recipecoll2.ui.fragment.start

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import com.example.recipecoll2.ui.mapper.toRecipeView
import com.example.recipecoll2.ui.model.RecipeView
import kotlinx.coroutines.launch


class StartViewModel @ViewModelInject constructor (
    private val recipeInteractor: RecipeInteractor
) : ViewModel() {

    val recipeMutableLiveData = MutableLiveData<MutableList<RecipeView>>()

    fun getData() {
        viewModelScope.launch {
            val data = recipeInteractor.getData().map { it.toRecipeView() }.toMutableList()
            recipeMutableLiveData.postValue(data)
        }
    }

    fun updateInFavorites(recipeId: Int) {
        viewModelScope.launch {
            recipeInteractor.updateRecipe(recipeId, 1)
            getData()
        }
    }

    fun updateOutFavorites(recipeId: Int) {
        viewModelScope.launch {
            recipeInteractor.updateRecipe(recipeId, 0)
            getData()
        }
    }
}