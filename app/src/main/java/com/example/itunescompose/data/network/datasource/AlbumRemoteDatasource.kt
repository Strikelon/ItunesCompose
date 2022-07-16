package com.example.itunescompose.data.network.datasource

import com.example.itunescompose.data.network.NetworkApi
import com.example.itunescompose.data.network.response.AlbumInfoListResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRemoteDatasource @Inject constructor(
    private val networkApi: NetworkApi
) {
    suspend fun getAlbumsInfoList(term: String): AlbumInfoListResponse =
        networkApi.getAlbumsInfoList(term)
}