package com.yogi.tvmazeapp.di

import com.yogi.tvmazeapp.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel{FavoriteViewModel(get())}
}