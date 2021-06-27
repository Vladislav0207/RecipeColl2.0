package com.freeze.domain.domain

import com.freeze.domain.domain.model.DomainRecipe


interface NetworkRepository {
    suspend fun getRemoteDataRecipe(): MutableList<DomainRecipe>
}