package com.example.recipecoll2.ui.fragment.resultSearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import com.example.recipecoll2.ui.mapper.toIngredientOnlyName
import com.example.recipecoll2.ui.mapper.toRecipeView
import com.example.recipecoll2.ui.model.IngredientOnlyNameView
import com.example.recipecoll2.ui.model.RecipeView
import kotlinx.coroutines.launch


class ResultSearchViewModel @ViewModelInject constructor(
    private val recipeInteractor: RecipeInteractor
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