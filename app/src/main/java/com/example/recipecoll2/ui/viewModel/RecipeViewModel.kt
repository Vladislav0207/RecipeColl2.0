package com.example.recipecoll2.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipecoll2.database.model.Recipe
import com.example.recipecoll2.domain.model.IngredientForView
import kotlinx.coroutines.*

class   RecipeViewModel (val repository: Repository) : ViewModel() {

    var showRecipe : Recipe? = null

    var ingredientsView = mutableSetOf<IngredientForView>()

    val scope = CoroutineScope(Dispatchers.IO)


    val favoriteList = mutableListOf<Recipe>()

    val recipeLive : MutableLiveData<MutableList<Recipe>> by lazy {
        MutableLiveData<MutableList<Recipe>>()
    }

    val recipeResult = mutableListOf<Recipe>()


    var listOfIngredientSelected = mutableListOf<IngredientForView>()

    fun getData() {
        scope.launch {
            val data = repository.getData()
            recipeLive.postValue(data)
        }
   }

    fun updateRecipe(recipeId:Int,isSelected:Int){
        scope.launch {
            repository.updateRecipe(recipeId,isSelected)
        }
    }

    fun updateInFavorites(position :Int){
        scope.launch {
            updateRecipe(recipeLive.value!![position].id,1)
            recipeLive.value!![position].isFavorite = 1
            favoriteList.add(recipeLive.value!![position])
        }
    }

    fun updateOutFavorites(recipeId: Int){
        scope.launch {
            updateRecipe(recipeId,0)
            favoriteList.removeIf{it.id == recipeId}
        }
    }

    fun getAllIngredientsView() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = repository.getAllIngredientsByView()
            ingredientsView = data
        }
    }

    fun searchByIngredient(){
        CoroutineScope(Dispatchers.IO).launch {
            recipeResult.clear()
            val data = repository.searchByIngredient(listOfIngredientSelected)
            recipeResult.addAll(data)
            return@launch
        }
    }

    fun getFavorites() {
        CoroutineScope(Dispatchers.Main).launch  {
            val data = repository.getFavorites()
            Log.d("!!!RRR",repository.getFavorites().toString())
            favoriteList.clear()
            favoriteList.addAll(data)
        }
    }
}