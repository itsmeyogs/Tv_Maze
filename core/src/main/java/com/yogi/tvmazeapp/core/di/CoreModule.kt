package com.yogi.tvmazeapp.core.di

import android.os.Build
import androidx.multidex.BuildConfig
import androidx.room.Room
import com.yogi.tvmazeapp.core.data.source.TvMazeRepository
import com.yogi.tvmazeapp.core.data.source.local.LocalDataSource
import com.yogi.tvmazeapp.core.data.source.local.room.TvMazeDatabase
import com.yogi.tvmazeapp.core.data.source.remote.RemoteDataSource
import com.yogi.tvmazeapp.core.data.source.remote.network.ApiService
import com.yogi.tvmazeapp.core.domain.repository.ITvMazeRepository
import com.yogi.tvmazeapp.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<TvMazeDatabase>().tvMazeDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            TvMazeDatabase::class.java, "TvMaze.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }else{
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITvMazeRepository> { TvMazeRepository(get(), get(), get()) }
}