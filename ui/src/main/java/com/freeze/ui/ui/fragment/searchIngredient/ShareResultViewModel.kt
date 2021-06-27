package com.freeze.ui.ui.fragment.searchIngredient

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freeze.ui.ui.model.IngredientOnlyNameView
import kotlinx.coroutines.launch

class ShareResultViewModel @ViewModelInject constructor(
) : ViewModel() {

    val changedIngredients = MutableLiveData<MutableList<IngredientOnlyNameView>>()

    fun getChangedIngredients(ingredientsOnlyNameView: MutableList<IngredientOnlyNameView>) {
        viewModelScope.launch {
            val data = ingredientsOnlyNameView
                .filter { it.isSelect }
                .toMutableList()
            changedIngredients.postValue(data)
        }
    }
}