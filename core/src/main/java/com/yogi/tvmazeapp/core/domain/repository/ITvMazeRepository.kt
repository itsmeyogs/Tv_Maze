package com.yogi.tvmazeapp.core.domain.repository

import com.yogi.tvmazeapp.core.data.source.Resource
import com.yogi.tvmazeapp.core.domain.model.TvMaze
import kotlinx.coroutines.flow.Flow

interface ITvMazeRepository {

    fun getAllShow(): Flow<Resource<List<TvMaze>>>

    fun getFavoriteShow(): Flow<List<TvMaze>>

    fun setFavoriteShow(show: TvMaze, state: Boolean)

}