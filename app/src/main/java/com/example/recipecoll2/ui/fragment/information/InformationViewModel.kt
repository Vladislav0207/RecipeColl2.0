package com.example.recipecoll2.ui.fragment.information

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import com.example.recipecoll2.ui.mapper.toRecipeView
import com.example.recipecoll2.ui.model.RecipeView
import kotlinx.coroutines.launch


class InformationViewModel @ViewModelInject constructor(
    private val recipeInteractor: RecipeInteractor
) : ViewModel() {

    val informationMutableLiveData = MutableLiveData<RecipeView>()
    fun getRecipeById(id: Int) {
        viewModelScope.launch {
            val recipe = recipeInteractor.getRecipeById(id).toRecipeView()
            informationMutableLiveData.postValue(recipe)
        }
    }
}