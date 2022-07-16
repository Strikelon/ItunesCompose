package com.example.itunescompose.data.repository

import com.example.itunescompose.core.coroutines.DispatcherProvider
import com.example.itunescompose.data.network.datasource.AlbumRemoteDatasource
import com.example.itunescompose.data.network.response.AlbumInfoListResponse
import com.example.itunescompose.specs.api.repository.AlbumRepositoryApi
import com.example.itunescompose.specs.entity.AlbumInfoDto
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumRemoteDatasource: AlbumRemoteDatasource,
    private val dispatcherProvider: DispatcherProvider
): AlbumRepositoryApi {

    override suspend fun getAlbumsInfoList(term: String): Result<List<AlbumInfoDto>> =
        withContext(dispatcherProvider.IO) {
            runCatching { albumRemoteDatasource.getAlbumsInfoList(term) }
                .map { albumInfoListResponse: AlbumInfoListResponse ->
                    val albumInfoDtoList = mutableListOf<AlbumInfoDto>()
                    albumInfoListResponse.results.forEach { albumInfoResponse: AlbumInfoListResponse.AlbumInfoResponse ->
                        albumInfoDtoList.add(
                            AlbumInfoDto(
                                collectionId = albumInfoResponse.collectionId,
                                collectionName = albumInfoResponse.collectionName,
                                artworkUrl100 = albumInfoResponse.artworkUrl100
                            )
                        )
                    }
                    albumInfoDtoList
                }
        }
}