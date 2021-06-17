package com.example.recipecoll2.ui.fragment.information

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.recipecoll2.ui.model.RecipeView


class InformationViewModel @ViewModelInject constructor(
) : ViewModel() {

    var informationMutableLiveData : RecipeView? = null
}