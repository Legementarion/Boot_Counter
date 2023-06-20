package com.lego.bootcounter.data.repository

import com.lego.bootcounter.data.datasource.LocalDataSource
import com.lego.bootcounter.data.models.Counter
import com.lego.bootcounter.data.models.toData
import com.lego.bootcounter.data.models.toDomain
import com.lego.bootcounter.domain.models.CounterModel
import com.lego.bootcounter.domain.repositories.Repository

class CounterRepository(private val dataSource: LocalDataSource) : Repository {

    override suspend fun getCounter(): CounterModel {
        return dataSource.getCounter().toDomain()
    }

    override suspend fun saveCounter(counter: CounterModel) {
        dataSource.save(counter.toData())
    }

}