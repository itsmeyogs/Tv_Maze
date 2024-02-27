package com.yogi.tvmazeapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Image(

	@field:SerializedName("original")
	val original: String,

	@field:SerializedName("medium")
	val medium: String
)

data class ShowsResponseItem(

    @field:SerializedName("summary")
	val summary: String,

    @field:SerializedName("image")
	val image: Image,

    @field:SerializedName("genres")
	val genres: List<String>,

    @field:SerializedName("name")
	val name: String,

    @field:SerializedName("id")
	val id: Int
)
