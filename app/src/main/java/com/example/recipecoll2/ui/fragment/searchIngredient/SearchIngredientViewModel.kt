package com.example.recipecoll2.ui.fragment.searchIngredient

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchIngredientViewModel (private val context: Context,
                                 private val recipeInteractor: RecipeInteractor
) : ViewModel()  {


    fun searchByIngredient(){
        viewModelScope.launch {
            recipeResult.clear()
            val data = recipeInteractor.searchByIngredient(listOfIngredientSelected)
            recipeResult.addAll(data)
        }
    }
}