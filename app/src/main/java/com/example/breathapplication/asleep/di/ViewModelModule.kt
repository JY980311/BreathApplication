package com.example.breathapplication.asleep.di

import android.app.Application
import com.example.breathapplication.asleep.viewmodel.AsleepViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun provideAsleepViewModel(
        applicationContext: Application
    ): AsleepViewModel {
        return AsleepViewModel(applicationContext)
    }
}