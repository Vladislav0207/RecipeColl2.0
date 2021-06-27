package com.freeze.ui.ui.fragment.searchIngredient

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freeze.ui.ui.mapper.toIngredientOnlyNameView
import com.freeze.ui.ui.model.IngredientOnlyNameView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchIngredientViewModel @Inject constructor(
    private val recipeInteractor: com.freeze.domain.domain.RecipeInteractor
) : ViewModel() {

    val ingredientOnlyNameViewMutableLiveData = MutableLiveData<MutableList<IngredientOnlyNameView>>()

    fun getAllIngredientsOnlyNameView() {
        viewModelScope.launch {
            val data = recipeInteractor.getAllIngredientsOnlyName().
            map { it.toIngredientOnlyNameView()}.
            sortedBy { it.name }.
            toMutableList()
            ingredientOnlyNameViewMutableLiveData.postValue(data)
        }
    }
}