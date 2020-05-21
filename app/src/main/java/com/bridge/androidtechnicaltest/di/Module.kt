package com.bridge.androidtechnicaltest.di

import com.bridge.androidtechnicaltest.db.IPupilRepository
import com.bridge.androidtechnicaltest.db.PupilRepository
import com.bridge.androidtechnicaltest.ui.MainActivityViewModel
import com.bridge.androidtechnicaltest.ui.PupilAddViewModel
import com.bridge.androidtechnicaltest.ui.PupilViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module

import org.koin.dsl.module

val networkModule = module {
    factory { PupilAPIFactory.retrofitPupil() }
}

val databaseModule = module {
    factory { DatabaseFactory.getDBInstance(get()) }
    single<IPupilRepository> { PupilRepository(get(), get()) }
}

val viewModels = module {
    viewModel {
        PupilViewModel(repository = get())
    }
    viewModel {
        MainActivityViewModel()
    }
    viewModel {
        PupilAddViewModel(get())
    }

}



