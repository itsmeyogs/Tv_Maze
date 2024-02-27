package com.yogi.tvmazeapp.core.domain.usecase

import com.yogi.tvmazeapp.core.data.source.Resource
import com.yogi.tvmazeapp.core.domain.model.TvMaze
import com.yogi.tvmazeapp.core.domain.repository.ITvMazeRepository
import kotlinx.coroutines.flow.Flow

class TvMazeInteractor(private val tvMazeRepository: ITvMazeRepository): TvMazeUseCase {

    override fun getALlShow(): Flow<Resource<List<TvMaze>>> = tvMazeRepository.getAllShow()

    override fun getFavoriteShow(): Flow<List<TvMaze>> = tvMazeRepository.getFavoriteShow()

    override fun setFavoriteShow(show: TvMaze, state: Boolean) = tvMazeRepository.setFavoriteShow(show, state)
}