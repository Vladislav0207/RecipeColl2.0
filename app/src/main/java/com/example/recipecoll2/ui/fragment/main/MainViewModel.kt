package com.example.recipecoll2.ui.fragment.main

import android.content.Context
import android.util.Log
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


class MainViewModel @ViewModelInject constructor (
    private val recipeInteractor: RecipeInteractor
) : ViewModel() {

    val recipeMutableLiveData = MutableLiveData<MutableList<RecipeView>>()

    fun getData() {
        viewModelScope.launch {
            Log.d("!!!", "Start get")
            val data = recipeInteractor.getData().map { it.toRecipeView() }.toMutableList()
            Log.d("!!!", data.toString())
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