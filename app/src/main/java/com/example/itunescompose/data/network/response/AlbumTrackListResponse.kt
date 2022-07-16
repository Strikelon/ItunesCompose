package com.example.itunescompose.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumTrackListResponse(
    @Json(name = "results")
    val results: List<AlbumTrackResponse>
) {
    @JsonClass(generateAdapter = true)
    data class AlbumTrackResponse(
        @Json(name = "wrapperType")
        val wrapperType: String,
        @Json(name = "trackName")
        val trackName: String?,
        @Json(name = "trackPrice")
        val trackPrice: String?,
        @Json(name = "currency")
        val currency: String,
        @Json(name = "artistName")
        val artistName: String,
        @Json(name = "collectionName")
        val collectionName: String,
        @Json(name = "artworkUrl100")
        val artworkUrl100: String,
        @Json(name = "trackCount")
        val trackCount: String,
        @Json(name = "collectionPrice")
        val collectionPrice: String,
        @Json(name = "primaryGenreName")
        val primaryGenreName: String
    )
}
