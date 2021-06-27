package com.freeze.ui.ui.fragment.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freeze.ui.ui.mapper.toRecipeView
import com.freeze.ui.ui.model.RecipeView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val recipeInteractor: com.freeze.domain.domain.RecipeInteractor
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