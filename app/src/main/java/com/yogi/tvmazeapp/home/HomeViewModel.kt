package com.yogi.tvmazeapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yogi.tvmazeapp.core.domain.usecase.TvMazeUseCase

class HomeViewModel(tvMazeUseCase: TvMazeUseCase):ViewModel() {
    val listShow = tvMazeUseCase.getALlShow().asLiveData()
}
