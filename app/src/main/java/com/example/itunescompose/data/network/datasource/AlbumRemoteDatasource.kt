package com.example.itunescompose.data.network.datasource

import com.example.itunescompose.data.network.NetworkApi
import com.example.itunescompose.data.network.response.AlbumInfoListResponse
import com.example.itunescompose.data.network.response.AlbumTrackListResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRemoteDatasource @Inject constructor(
    private val networkApi: NetworkApi
) {
    suspend fun getAlbumsInfoList(term: String): AlbumInfoListResponse =
        networkApi.getAlbumsInfoList(term)

    suspend fun getAlbumTracksById(id: String): AlbumTrackListResponse =
        networkApi.getAlbumTracksById(id)
}