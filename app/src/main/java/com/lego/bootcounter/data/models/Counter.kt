package com.lego.bootcounter.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Counter(
    val count: Int,
    @PrimaryKey
    val lastTime: Long
)