package com.example.recipecoll2.repository

import com.example.recipecoll2.database.LocalModel
import com.example.recipecoll2.database.LocalRecipe
import com.example.recipecoll2.network.*
import javax.inject.Inject

class Repository @Inject constructor (
                  val remoteModel: RemoteModel,
                  val localModel: LocalModel
) {
    suspend fun getData(): MutableList<Recipe> {
     val recipeList = remoteModel.getRemoteDataRecipe()
    //  val recipeList : MutableList<Recipe>? = null
        return if (recipeList != null) {


            val localRecipeList = mutableListOf<LocalRecipe>()
            recipeList.mapTo(localRecipeList){LocalRecipe( it.id,
                it.title,
                it.readyInMinutes,
                it.servings,
                it.image,
                it.instructions,
                it.isFavorite)}

            localModel.insertRecipes(localRecipeList)
            for (i in 0 until recipeList.size ) {
                localModel.insertIngredients(recipeList[i].extendedIngredients)
            }


           val finishList = localModel.getAllRecipes()
            finishList.toMutableList()


        } else {
            val finishList = localModel.getAllRecipes()
            finishList.toMutableList()
        }
    }


    suspend fun updateRecipe(recipeId:Int,isSelected:Int){
        localModel.updateRecipe(recipeId, isSelected)
    }

    suspend fun getAllIngredientsByView(): MutableSet<IngredientView>{
        val listIngredients = localModel.getAllIngredients()
        val setIngredientsByView = listIngredients.mapTo(mutableSetOf<IngredientView>()){IngredientView(
            it.nameClean
        )
        }
        return setIngredientsByView
    }

    suspend fun searchByIngredient (listOfNames : MutableList<String>) : MutableList<Recipe>{
       val recipeList= getData()
        val resultList = mutableListOf<Recipe>()

        for (i in 0 until  recipeList.size){
//           -- recipeList[i]--

            var h =0
            for (j in 0 until  recipeList[i].extendedIngredients.size) {
//               -- recipeList[i].extendedIngredients[j]--

                for (k in 0 until  listOfNames.size){

                    if (recipeList[i].extendedIngredients[j].nameClean == listOfNames[k]){
                        h+=1
                    }
                }
            }


            if (h == listOfNames.size){
                resultList.add(recipeList[i])
            }
        }
        return resultList
    }

    suspend fun getFavorites():MutableList<Recipe>{
        val List = localModel.getAllRecipes().filter { it.isFavorite == 1 }
        val listOfFavorite = List.toMutableList()
        return listOfFavorite
    }
}