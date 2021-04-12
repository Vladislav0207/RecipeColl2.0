package com.example.recipecoll2.network.hilt

import com.example.recipecoll2.database.repository.DatabaseRepositoryImpl
import com.example.recipecoll2.domain.DatabaseRepository
import com.example.recipecoll2.domain.NetworkRepository
import com.example.recipecoll2.network.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class NetworkModule {

    @ActivityScoped
    @Binds
    abstract fun bindNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository
}