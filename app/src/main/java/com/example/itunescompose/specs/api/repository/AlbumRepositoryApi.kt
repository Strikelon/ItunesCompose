package com.example.itunescompose.specs.api.repository

import com.example.itunescompose.specs.entity.AlbumInfoDto
import com.example.itunescompose.specs.entity.AlbumTrackInfoDto

interface AlbumRepositoryApi {

    suspend fun getAlbumsInfoList(term: String): Result<List<AlbumInfoDto>>

    suspend fun getAlbumTracksById(id: String): Result<AlbumTrackInfoDto>
}