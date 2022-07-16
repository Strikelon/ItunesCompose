package com.example.itunescompose.data.network

import com.example.itunescompose.data.network.response.AlbumInfoListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET(SEARCH)
    suspend fun getAlbumsInfoList(
        @Query("term") term: String,
        @Query("media") media: String = "music",
        @Query("entity") entity: String = "album"
    ): AlbumInfoListResponse

    companion object {
        private const val SEARCH = "search"
        private const val LOOKUP = "lookup"
    }
}