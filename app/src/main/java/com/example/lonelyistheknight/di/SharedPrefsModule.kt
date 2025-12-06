package com.example.lonelyistheknight.di

import android.content.Context
import com.example.lonelyistheknight.data.sharedPref.SharedPrefsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefsModule {

    @Provides
    @Singleton
    fun provideSharedPrefsManager(
        @ApplicationContext context: Context
    ): SharedPrefsManager {
        return SharedPrefsManager(context)
    }
}
