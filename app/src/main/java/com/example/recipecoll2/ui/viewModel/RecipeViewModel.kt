package com.example.recipecoll2.ui.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipecoll2.database.model.Recipe
import com.example.recipecoll2.domain.RecipeInteractor
import com.example.recipecoll2.domain.model.IngredientOnlyName
import kotlinx.coroutines.*

class    (private val context: Context,
                         private val recipeInteractor: RecipeInteractor
                         ) : ViewModel() {

    var showRecipe : Recipe? = null

    var ingredientsView = mutableSetOf<IngredientOnlyName>()

    val scope = CoroutineScope(Dispatchers.IO)


    val favoriteList = mutableListOf<Recipe>()

    val recipeLive : MutableLiveData<MutableList<Recipe>> by lazy {
        MutableLiveData<MutableList<Recipe>>()
    }

    val recipeResult = mutableListOf<Recipe>()


    var listOfIngredientSelected = mutableListOf<IngredientOnlyName>()

    fun getData() {
        scope.launch {
            val data = repository.getData()
            recipeLive.postValue(data)
        }
   }



    fun updateInFavorites(position :Int){
        scope.launch {
            recipeInteractor.updateRecipe(recipeLive.value!![position].id,1)
            recipeLive.value!![position].isFavorite = 1
            favoriteList.add(recipeLive.value!![position])
        }
    }

    fun updateOutFavorites(recipeId: Int){
        scope.launch {
            recipeInteractor.updateRecipe(recipeId,0)
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
            favoriteList.clear()
            favoriteList.addAll(data)
        }
    }
}