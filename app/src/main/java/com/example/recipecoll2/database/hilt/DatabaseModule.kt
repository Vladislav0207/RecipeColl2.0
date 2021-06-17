package com.example.recipecoll2.database.hilt

import com.example.recipecoll2.database.repository.DatabaseRepositoryImpl
import com.example.recipecoll2.domain.DatabaseRepository
import com.example.recipecoll2.domain.RecipeInteractor
import com.example.recipecoll2.domain.RecipeInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton





@Module
@InstallIn(ActivityComponent::class)
abstract class DatabaseRepositoryModule {

    @ActivityScoped
    @Binds
    abstract fun bindDatabaseRepository(databaseRepositoryImpl: DatabaseRepositoryImpl): DatabaseRepository
}
