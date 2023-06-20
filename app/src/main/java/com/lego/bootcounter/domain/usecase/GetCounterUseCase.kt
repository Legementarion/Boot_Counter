package com.lego.bootcounter.domain.usecase

import com.lego.bootcounter.domain.repositories.Repository

class GetCounterUseCase(private val repo: Repository) {

    suspend fun getCounter() = repo.getCounter()

}