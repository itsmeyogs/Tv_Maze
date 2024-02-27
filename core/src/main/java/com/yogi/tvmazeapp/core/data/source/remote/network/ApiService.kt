package com.yogi.tvmazeapp.core.data.source.remote.network

import com.yogi.tvmazeapp.core.data.source.remote.response.ShowsResponseItem
import retrofit2.http.GET

interface ApiService {
    @GET("shows")
    suspend fun getListShows(): List<ShowsResponseItem>
}