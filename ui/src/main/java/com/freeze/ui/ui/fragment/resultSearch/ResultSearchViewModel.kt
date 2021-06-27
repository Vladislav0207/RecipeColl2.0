package com.freeze.ui.ui.fragment.resultSearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freeze.ui.ui.mapper.toIngredientOnlyName
import com.freeze.ui.ui.mapper.toRecipeView
import com.freeze.ui.ui.model.IngredientOnlyNameView
import com.freeze.ui.ui.model.RecipeView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultSearchViewModel @Inject constructor(
    private val recipeInteractor: com.freeze.domain.domain.RecipeInteractor
) : ViewModel() {
    val recipeResultMutableLiveData = MutableLiveData<MutableList<RecipeView>>()

    fun getResult(ingredientOnlyNameView: MutableList<IngredientOnlyNameView>){
        viewModelScope.launch {
            val ingredientOnlyName = ingredientOnlyNameView
                .map { it.toIngredientOnlyName() }
                .toMutableList()
            val data = recipeInteractor.searchByIngredient(ingredientOnlyName)
                .map { it.toRecipeView() }
                .toMutableList()
            recipeResultMutableLiveData.postValue(data)
        }
    }

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