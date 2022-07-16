package com.example.itunescompose.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumInfoListResponse(
    @Json(name = "results")
    val results: List<AlbumInfoResponse>
) {
    @JsonClass(generateAdapter = true)
    data class AlbumInfoResponse(
        @Json(name = "collectionId")
        val collectionId: String,
        @Json(name = "collectionName")
        val collectionName: String,
        @Json(name = "artworkUrl100")
        val artworkUrl100: String
    )
}
