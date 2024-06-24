package com.yogi.tvmazeapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_maze")
data class TvMazeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "genres")
    var genres:String,

    @ColumnInfo(name = "image")
    var image:String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)