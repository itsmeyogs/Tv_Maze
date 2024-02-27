package com.yogi.tvmazeapp.core.data.source

import com.yogi.tvmazeapp.core.data.source.local.LocalDataSource
import com.yogi.tvmazeapp.core.data.source.remote.RemoteDataSource
import com.yogi.tvmazeapp.core.data.source.remote.network.ApiResponse
import com.yogi.tvmazeapp.core.data.source.remote.response.ShowsResponseItem
import com.yogi.tvmazeapp.core.domain.model.TvMaze
import com.yogi.tvmazeapp.core.domain.repository.ITvMazeRepository
import com.yogi.tvmazeapp.core.utils.AppExecutors
import com.yogi.tvmazeapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TvMazeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITvMazeRepository {

    override fun getAllShow(): Flow<Resource<List<TvMaze>>> =
        object : NetworkBoundResource<List<TvMaze>, List<ShowsResponseItem>>(){
            override fun loadFromDB(): Flow<List<TvMaze>> {
                return localDataSource.getAllShow().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvMaze>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ShowsResponseItem>>> = remoteDataSource.getAllShow()

            override suspend fun saveCallResult(data: List<ShowsResponseItem>) {
                val showList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertShow(showList)
            }
        }.asFlow()


    override fun getFavoriteShow(): Flow<List<TvMaze>> {
        return localDataSource.getFavoriteShow().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteShow(show: TvMaze, state: Boolean) {
        val tvMazeEntity = DataMapper.mapDomainToEntity(show)
        appExecutors.diskIO().execute{localDataSource.setFavoriteShow(tvMazeEntity, state)}
    }

}
