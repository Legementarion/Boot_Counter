package com.lego.bootcounter.data.datasource

import com.lego.bootcounter.data.models.Counter

interface LocalDataSource {

    suspend fun save(counter: Counter)

    suspend fun getCounter(): Counter

}