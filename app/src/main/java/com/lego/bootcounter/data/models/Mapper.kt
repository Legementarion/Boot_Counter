package com.lego.bootcounter.data.models

import com.lego.bootcounter.domain.models.CounterModel

fun Counter.toDomain() = CounterModel(
    this.count,
    this.lastTime
)

fun CounterModel.toData() = Counter(
    this.count,
    this.lastBootTime
)