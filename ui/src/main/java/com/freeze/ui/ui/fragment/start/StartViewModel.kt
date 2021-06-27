package com.freeze.ui.ui.fragment.start

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freeze.domain.domain.RecipeInteractor
import com.freeze.ui.ui.mapper.toRecipeView
import com.freeze.ui.ui.model.RecipeView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor (
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