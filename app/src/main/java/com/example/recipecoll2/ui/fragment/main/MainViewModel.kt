package com.example.recipecoll2.ui.fragment.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import com.example.recipecoll2.ui.mapper.toRecipeView
import com.example.recipecoll2.ui.model.RecipeView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val context: Context,
    private val recipeInteractor: RecipeInteractor
) : ViewModel() {

    val recipeMutableLiveData = MutableLiveData<MutableList<RecipeView>>()

    fun getData() {
        viewModelScope.launch {
            val data = recipeInteractor.getData().map { it.toRecipeView() }.toMutableList()
            recipeMutableLiveData.postValue(data)
        }
    }

    fun updateInFavorites(position: Int) {
        viewModelScope.launch {
            recipeInteractor.updateRecipe(recipeMutableLiveData.value!![position].id, 1)
        }
    }

    fun updateOutFavorites(recipeId: Int) {
        viewModelScope.launch {
            recipeInteractor.updateRecipe(recipeId, 0)
        }
    }
}