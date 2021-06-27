package com.freeze.ui.ui.fragment.information

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.freeze.ui.ui.model.RecipeView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
) : ViewModel() {

    var informationMutableLiveData : RecipeView? = null
}