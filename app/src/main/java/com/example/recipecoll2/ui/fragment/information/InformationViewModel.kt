package com.example.recipecoll2.ui.fragment.information

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import com.example.recipecoll2.ui.mapper.toRecipeView
import com.example.recipecoll2.ui.model.RecipeView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InformationViewModel (private val context: Context,
                            private val recipeInteractor: RecipeInteractor
) : ViewModel()  {

    val informationMutableLiveData = MutableLiveData<RecipeView>()
    fun getRecipeById(id:Int) {
        viewModelScope.launch {
           val recipe = recipeInteractor.getRecipeById(id).toRecipeView()
            informationMutableLiveData.postValue(recipe)
        }
    }
}