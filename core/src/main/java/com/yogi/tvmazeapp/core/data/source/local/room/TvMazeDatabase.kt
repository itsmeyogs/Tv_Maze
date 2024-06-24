package com.yogi.tvmazeapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yogi.tvmazeapp.core.data.source.local.entity.TvMazeEntity

@Database(entities = [TvMazeEntity::class], version = 1, exportSchema = false)
abstract class TvMazeDatabase : RoomDatabase() {

    abstract fun tvMazeDao(): TvMazeDao

}