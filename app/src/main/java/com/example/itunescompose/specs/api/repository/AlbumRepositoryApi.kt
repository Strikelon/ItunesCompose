package com.example.itunescompose.specs.api.repository

import com.example.itunescompose.specs.entity.AlbumInfoDto

interface AlbumRepositoryApi {

    suspend fun getAlbumsInfoList(term: String): Result<List<AlbumInfoDto>>
}