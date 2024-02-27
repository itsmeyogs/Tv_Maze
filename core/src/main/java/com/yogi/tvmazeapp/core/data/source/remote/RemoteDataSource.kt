package com.yogi.tvmazeapp.core.data.source.remote

import android.util.Log
import com.yogi.tvmazeapp.core.data.source.remote.network.ApiResponse
import com.yogi.tvmazeapp.core.data.source.remote.network.ApiService
import com.yogi.tvmazeapp.core.data.source.remote.response.ShowsResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllShow():Flow<ApiResponse<List<ShowsResponseItem>>>{
        return flow{
            try {
                val response = apiService.getListShows()
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

