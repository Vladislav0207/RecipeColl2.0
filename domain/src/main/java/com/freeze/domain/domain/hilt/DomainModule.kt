package com.freeze.domain.domain.hilt

import com.freeze.domain.domain.RecipeInteractor
import com.freeze.domain.domain.RecipeInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindRecipeInterator(recipeInteratorImpl: RecipeInteractorImpl): RecipeInteractor
}