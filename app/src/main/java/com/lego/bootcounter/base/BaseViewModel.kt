package com.lego.bootcounter.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lego.bootcounter.data.models.Resource
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    private val job = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)
    val isLoading = MutableLiveData<Boolean>()

    fun executeInCoroutine(block: suspend CoroutineScope.() -> Unit) {
        handleLoading()
        viewModelScope.launch {
            block.invoke(this)
        }
    }



    protected fun handleLoading() {
        isLoading.postValue(true)
    }

    protected fun handleError(response: Resource.Failure) {
        isLoading.value = false
    }

    fun <T> Resource<T>.handleResponse(
        onSuccess: (T) -> Unit,
        onError: ((Resource.Failure) -> Unit?)? = null,
        onLoading: () -> Unit = { handleLoading() }
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            when (this@handleResponse) {
                is Resource.Failure -> {
                    handleError(this@handleResponse)
                    onError?.invoke(this@handleResponse)
                }
                is Resource.Loading -> onLoading()
                is Resource.Success -> {
                    isLoading.value = false
                    onSuccess(this@handleResponse.value)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}