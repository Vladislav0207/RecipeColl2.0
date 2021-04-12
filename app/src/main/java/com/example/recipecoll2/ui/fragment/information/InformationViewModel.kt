package com.example.recipecoll2.ui.fragment.information

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecoll2.domain.RecipeInteractor
import com.example.recipecoll2.ui.mapper.toRecipeView
import com.example.recipecoll2.ui.model.RecipeView
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch


class InformationViewModel @ViewModelInject constructor(
    private val recipeInteractor: RecipeInteractor
) : ViewModel() {

    var informationMutableLiveData : RecipeView? = null

}