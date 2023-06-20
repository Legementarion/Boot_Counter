package com.lego.bootcounter.domain.repositories

import com.lego.bootcounter.domain.models.CounterModel

interface Repository {

    suspend fun getCounter(): CounterModel

    suspend fun saveCounter(counter: CounterModel)

}