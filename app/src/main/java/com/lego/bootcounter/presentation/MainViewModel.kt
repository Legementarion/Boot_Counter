package com.lego.bootcounter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lego.bootcounter.base.BaseViewModel
import com.lego.bootcounter.domain.models.CounterModel
import com.lego.bootcounter.domain.usecase.GetCounterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val getCounterUseCase: GetCounterUseCase) : BaseViewModel() {

    private val _counter: MutableLiveData<CounterModel> = MutableLiveData()
    val counter: LiveData<CounterModel> = _counter

    init {
        executeInCoroutine {
            _counter.postValue(getCounterUseCase.getCounter())
        }
    }

}