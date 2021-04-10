package com.example.recipecoll2.domain

import com.example.recipecoll2.domain.model.DomainRecipe


interface NetworkRepository {
    suspend fun getRemoteDataRecipe() : MutableList<DomainRecipe>
}