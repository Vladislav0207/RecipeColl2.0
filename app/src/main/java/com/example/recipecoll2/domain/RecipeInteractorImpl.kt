package com.example.recipecoll2.domain

import com.example.recipecoll2.database.model.Recipe
import com.example.recipecoll2.domain.model.DomainRecipe
import com.example.recipecoll2.domain.model.IngredientForView

class RecipeInteractorImpl(
    private val databaseRepository: DatabaseRepository,
    private val networkRepository: NetworkRepository
) : RecipeInteractor {
    override suspend fun getData(): MutableList<DomainRecipe> {
       return if (networkRepository.getRemoteDataRecipe().isEmpty())
        {
            databaseRepository.getAllRecipes().toMutableList()
        }
        else{
            val recipes = networkRepository.getRemoteDataRecipe()
            databaseRepository.insertRecipes(recipes)
           recipes
       }
    }

    override suspend fun updateRecipe(recipeId: Int, isSelected: Int) {
        databaseRepository.updateRecipe(recipeId, isSelected)
    }

    override suspend fun getAllIngredientsForView(): MutableSet<IngredientForView> {
        return databaseRepository.getAllIngredients().mapTo(mutableSetOf<IngredientForView>()){
            IngredientForView(
                it.nameClean
            )
        }
    }

    override suspend fun searchByIngredient(listOfNames: MutableList<IngredientForView>): MutableList<DomainRecipe> {
        val recipeList= getData()
        val resultList = mutableListOf<DomainRecipe>()

        for (recipeCount in 0 until  recipeList.size)
        //           -- recipeList[i] -- for each recipe
        {
            var checkCount =0


            for (ingredientCount in 0 until  recipeList[recipeCount].extendedIngredients.size)
            //               -- recipeList[i].extendedIngredients[j]-- for each list of ingredients
            {


                for (ingredientSearchCount in 0 until  listOfNames.size)
                //                       -- from each ingredient from result of search --
                {


                    if (recipeList[recipeCount].extendedIngredients[ingredientCount].nameClean
                        ==
                        listOfNames[ingredientSearchCount].name)
                        {
                        checkCount+=1
                    }

                }
            }


            if (checkCount == listOfNames.size){
                resultList.add(recipeList[recipeCount])
            }
        }
        return resultList
    }

    override suspend fun getFavorites(): MutableList<DomainRecipe> {
      return  databaseRepository.getAllRecipes().filter { it.isFavorite == 1 }.toMutableList()
    }

}