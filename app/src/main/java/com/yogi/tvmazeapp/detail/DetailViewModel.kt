package com.yogi.tvmazeapp.detail

import androidx.lifecycle.ViewModel
import com.yogi.tvmazeapp.core.domain.model.TvMaze
import com.yogi.tvmazeapp.core.domain.usecase.TvMazeUseCase

class DetailViewModel(private val tvMazeUseCase: TvMazeUseCase): ViewModel() {
    fun setFavoriteShow(show: TvMaze, newStatus:Boolean){
        tvMazeUseCase.setFavoriteShow(show, newStatus)
    }
}