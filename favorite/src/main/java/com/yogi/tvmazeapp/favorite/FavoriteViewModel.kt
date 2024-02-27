package com.yogi.tvmazeapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yogi.tvmazeapp.core.domain.usecase.TvMazeUseCase

class FavoriteViewModel(tvMazeUseCase: TvMazeUseCase) : ViewModel() {
    val favoriteShow = tvMazeUseCase.getFavoriteShow().asLiveData()
}

