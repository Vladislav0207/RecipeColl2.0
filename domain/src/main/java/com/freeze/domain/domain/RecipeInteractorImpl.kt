package com.freeze.domain.domain

import com.freeze.domain.domain.model.DomainRecipe
import com.freeze.domain.domain.model.IngredientOnlyName
import javax.inject.Inject

class RecipeInteractorImpl @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val networkRepository: NetworkRepository
) : RecipeInteractor {
    override suspend fun getData(): MutableList<DomainRecipe> {
        return if (networkRepository.getRemoteDataRecipe().isEmpty()) {
            databaseRepository.getAllRecipes().toMutableList()
        } else {
            var recipes = networkRepository.getRemoteDataRecipe()
            databaseRepository.insertRecipes(recipes)
            recipes = databaseRepository.getAllRecipes().toMutableList()
            recipes
        }
    }

    override suspend fun updateRecipe(recipeId: Int, isSelected: Int) {
        databaseRepository.updateRecipe(recipeId, isSelected)
    }

    override suspend fun getAllIngredientsOnlyName(): MutableSet<IngredientOnlyName> {
        return databaseRepository.getAllIngredients().mapTo(mutableSetOf<IngredientOnlyName>()) {
            IngredientOnlyName(
                it.nameClean
            )
        }
    }

    override suspend fun searchByIngredient(listOfNames: MutableList<IngredientOnlyName>): MutableList<DomainRecipe> {
        val recipeList = getData()
        val resultList = mutableListOf<DomainRecipe>()

        for (recipeCount in 0 until recipeList.size)
        //           -- recipeList[i] -- for each recipe
        {
            var checkCount = 0


            for (ingredientCount in 0 until recipeList[recipeCount].extendedIngredients.size)
            //               -- recipeList[i].extendedIngredients[j]-- for each list of ingredients
            {


                for (ingredientSearchCount in 0 until listOfNames.size)
                //                       -- from each ingredient from result of search --
                {


                    if (recipeList[recipeCount].extendedIngredients[ingredientCount].nameClean
                        ==
                        listOfNames[ingredientSearchCount].name
                    ) {
                        checkCount += 1
                    }

                }
            }


            if (checkCount == listOfNames.size) {
                resultList.add(recipeList[recipeCount])
            }
        }
        return resultList
    }

    override suspend fun getFavorites(): MutableList<DomainRecipe> {
        return databaseRepository.getAllRecipes().filter { it.isFavorite == 1 }.toMutableList()
    }

    override suspend fun getRecipeById(id: Int): DomainRecipe {
        return databaseRepository.getRecipeById(id)
    }

}