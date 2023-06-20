package com.lego.bootcounter

import com.lego.bootcounter.data.dao.CounterDataBase
import com.lego.bootcounter.data.datasource.LocalDataSource
import com.lego.bootcounter.data.datasource.LocalDataSourceImpl
import com.lego.bootcounter.data.repository.CounterRepository
import com.lego.bootcounter.domain.repositories.Repository
import com.lego.bootcounter.domain.usecase.GetCounterUseCase
import com.lego.bootcounter.domain.usecase.SaveCounterUseCase
import com.lego.bootcounter.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

}

val presentation = module {
    viewModel { MainViewModel(get()) }
}

val domain = module {
    factory { SaveCounterUseCase(get()) }
    factory { GetCounterUseCase(get()) }
}

val data = module {
    single<Repository> { CounterRepository(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get()) }
    single { CounterDataBase.getInstance(get()).counterDao() }
}