package com.example.recipecoll2.ui.fragment.searchIngredient

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import com.example.recipecoll2.ui.mapper.toIngredientOnlyNameView
import com.example.recipecoll2.ui.mapper.toRecipeView
import com.example.recipecoll2.ui.model.IngredientOnlyNameView
import com.example.recipecoll2.ui.model.RecipeView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchIngredientViewModel @ViewModelInject constructor(
    private val recipeInteractor: RecipeInteractor
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