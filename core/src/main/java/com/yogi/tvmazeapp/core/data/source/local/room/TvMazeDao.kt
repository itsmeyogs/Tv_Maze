package com.yogi.tvmazeapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yogi.tvmazeapp.core.data.source.local.entity.TvMazeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvMazeDao {

    @Query("SELECT * FROM tv_maze")
    fun getAllShow(): Flow<List<TvMazeEntity>>

    @Query("SELECT * FROM tv_maze where isFavorite = 1")
    fun getFavoriteShow(): Flow<List<TvMazeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(show: List<TvMazeEntity>)

    @Update
    fun updateFavoriteShow(show: TvMazeEntity)

}