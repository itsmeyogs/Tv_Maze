package com.yogi.tvmazeapp.core.di

import com.yogi.tvmazeapp.core.BuildConfig
import androidx.room.Room
import com.yogi.tvmazeapp.core.data.source.TvMazeRepository
import com.yogi.tvmazeapp.core.data.source.local.LocalDataSource
import com.yogi.tvmazeapp.core.data.source.local.room.TvMazeDatabase
import com.yogi.tvmazeapp.core.data.source.remote.RemoteDataSource
import com.yogi.tvmazeapp.core.data.source.remote.network.ApiService
import com.yogi.tvmazeapp.core.domain.repository.ITvMazeRepository
import com.yogi.tvmazeapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("yogi".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            TvMazeDatabase::class.java, "TvMaze.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.tvmaze.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/c/0nAAuHA5w68X9XBR7OcxMVtuBYAPl9QRcDFgm6+QE=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(if(BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
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