package com.yogi.tvmazeapp.core.utils

import com.yogi.tvmazeapp.core.data.source.local.entity.TvMazeEntity
import com.yogi.tvmazeapp.core.data.source.remote.response.ShowsResponseItem
import com.yogi.tvmazeapp.core.domain.model.TvMaze
import org.jsoup.Jsoup

object DataMapper {
    fun mapResponsesToEntities(input: List<ShowsResponseItem>): List<TvMazeEntity> {
        val showList = ArrayList<TvMazeEntity>()
        input.map {
            val description = Jsoup.parse(it.summary).text()
            val show = TvMazeEntity(
                id = it.id,
                name = it.name,
                description = description,
                genres = it.genres.joinToString(","),
                image = it.image.original,
                isFavorite = false
            )
            showList.add(show)
        }
        return showList
    }

    fun mapEntitiesToDomain(input: List<TvMazeEntity>): List<TvMaze> =
        input.map {
            TvMaze(
                id = it.id,
                name = it.name,
                description = it.description,
                genres = it.genres,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: TvMaze) = TvMazeEntity(
        id = input.id,
        name = input.name,
        description = input.description,
        genres = input.genres,
        image = input.image,
        isFavorite = input.isFavorite
    )
}