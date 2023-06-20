package com.lego.bootcounter.data.datasource

import com.lego.bootcounter.data.dao.CounterDao
import com.lego.bootcounter.data.models.Counter

class LocalDataSourceImpl(private val dao: CounterDao) : LocalDataSource {

    override suspend fun save(counter: Counter) {
        dao.save(counter)
    }

    override suspend fun getCounter(): Counter {
        return dao.getCounter()
    }
}