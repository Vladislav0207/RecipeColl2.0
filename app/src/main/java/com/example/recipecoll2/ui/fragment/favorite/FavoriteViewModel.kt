package com.example.recipecoll2.ui.fragment.favorite

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import com.example.recipecoll2.ui.mapper.toRecipeView
import com.example.recipecoll2.ui.model.RecipeView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavoriteViewModel @ViewModelInject constructor(
    private val recipeInteractor: RecipeInteractor
) : ViewModel() {

    val favoriteMutableLiveData = MutableLiveData<MutableList<RecipeView>>()
    fun getFavorites() {
        viewModelScope.launch {
            val data = recipeInteractor.getFavorites().map { it.toRecipeView() }.toMutableList()
            favoriteMutableLiveData.postValue(data)
        }
    }

    fun updateOutFavorites(recipeId: Int) {
        viewModelScope.launch {
            recipeInteractor.updateRecipe(recipeId, 0)
            getFavorites()
        }
    }
}