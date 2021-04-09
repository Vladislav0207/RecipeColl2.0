package com.example.recipecoll2.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipecoll2.repository.Repository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RecipeViewModelFactory @Inject constructor (val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeViewModel(repository) as T
    }
}