package com.yogi.tvmazeapp

import android.app.Application
import com.yogi.tvmazeapp.core.di.databaseModule
import com.yogi.tvmazeapp.core.di.networkModule
import com.yogi.tvmazeapp.core.di.repositoryModule
import com.yogi.tvmazeapp.di.useCaseModule
import com.yogi.tvmazeapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}