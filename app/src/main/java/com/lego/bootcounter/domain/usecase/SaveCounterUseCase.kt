package com.lego.bootcounter.domain.usecase

import com.lego.bootcounter.domain.models.CounterModel
import com.lego.bootcounter.domain.repositories.Repository

class SaveCounterUseCase(private val repo: Repository) {

    suspend fun save(counter: CounterModel) {
        repo.saveCounter(counter)
    }

}