package com.yogi.tvmazeapp.di

import com.yogi.tvmazeapp.core.domain.usecase.TvMazeInteractor
import com.yogi.tvmazeapp.core.domain.usecase.TvMazeUseCase
import com.yogi.tvmazeapp.detail.DetailViewModel
import com.yogi.tvmazeapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TvMazeUseCase> { TvMazeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}