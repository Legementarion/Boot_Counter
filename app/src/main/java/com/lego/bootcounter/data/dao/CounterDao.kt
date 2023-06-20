package com.lego.bootcounter.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lego.bootcounter.data.models.Counter

@Dao
interface CounterDao {

    @Query("SELECT * FROM Counter")
    fun getCounter(): Counter

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(counter: Counter)

}