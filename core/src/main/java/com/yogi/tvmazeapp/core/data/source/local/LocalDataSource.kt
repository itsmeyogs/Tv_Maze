package com.yogi.tvmazeapp.core.data.source.local

import com.yogi.tvmazeapp.core.data.source.local.entity.TvMazeEntity
import com.yogi.tvmazeapp.core.data.source.local.room.TvMazeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val tvMazeDao: TvMazeDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tvMazeDao: TvMazeDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tvMazeDao)
            }
    }

    fun getAllShow(): Flow<List<TvMazeEntity>> = tvMazeDao.getAllShow()

    fun getFavoriteShow(): Flow<List<TvMazeEntity>> = tvMazeDao.getFavoriteShow()

    suspend fun insertShow(showList:List<TvMazeEntity>) = tvMazeDao.insertShow(showList)

    fun setFavoriteShow(show: TvMazeEntity, newState: Boolean){
        show.isFavorite = newState
        tvMazeDao.updateFavoriteShow(show)
    }

}