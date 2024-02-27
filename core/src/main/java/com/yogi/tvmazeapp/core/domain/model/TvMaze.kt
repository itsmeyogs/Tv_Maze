package com.yogi.tvmazeapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class TvMaze (
    val id:Int,
    val name:String,
    val description:String,
    val genres: String,
    val image: String,
    val isFavorite: Boolean
):Parcelable
