package org.newsReader.com.di

import androidx.room.Room
import org.newsReader.com.data.local.Database
import org.newsReader.com.data.remote.service.global.RemoteServiceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.newsReader.com.data.repositories.DataRepository
import org.newsReader.com.viewmodels.DataViewModel

val appModule = module {

    single {
        RemoteServiceFactory.makeRemoteService(true)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java, "newsSource.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { DataRepository(get(), get()) }
    viewModel { DataViewModel(get(),get()) }

}