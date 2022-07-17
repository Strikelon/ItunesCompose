package com.example.itunescompose.data.repository

import com.example.itunescompose.core.coroutines.DispatcherProvider
import com.example.itunescompose.data.network.datasource.AlbumRemoteDatasource
import com.example.itunescompose.data.network.response.AlbumInfoListResponse
import com.example.itunescompose.data.network.response.AlbumTrackListResponse
import com.example.itunescompose.specs.api.repository.AlbumRepositoryApi
import com.example.itunescompose.specs.entity.AlbumInfoDto
import com.example.itunescompose.specs.entity.AlbumTrackDto
import com.example.itunescompose.specs.entity.AlbumTrackInfoDto
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumRemoteDatasource: AlbumRemoteDatasource,
    private val dispatcherProvider: DispatcherProvider
) : AlbumRepositoryApi {

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

    override suspend fun getAlbumTracksById(id: String): Result<AlbumTrackInfoDto> =
        withContext(dispatcherProvider.IO) {
            runCatching { albumRemoteDatasource.getAlbumTracksById(id) }
                .map { albumTrackListResponse: AlbumTrackListResponse ->
                    val albumTrackDtoList = mutableListOf<AlbumTrackDto>()
                    albumTrackListResponse.results.forEach { albumTrackResponse ->
                        if (albumTrackResponse.wrapperType == WRAPPER_TYPE_TRACK) {
                            albumTrackDtoList.add(
                                AlbumTrackDto(
                                    trackName = albumTrackResponse.trackName ?: "",
                                    trackPrice = albumTrackResponse.trackPrice ?: "",
                                    currency = albumTrackResponse.currency
                                )
                            )
                        }
                    }
                    val albumInfo = albumTrackListResponse.results[0]
                    AlbumTrackInfoDto(
                        artistName = albumInfo.artistName,
                        collectionName = albumInfo.collectionName,
                        primaryGenreName = albumInfo.primaryGenreName,
                        artworkUrl100 = albumInfo.artworkUrl100,
                        trackCount = albumInfo.trackCount,
                        collectionPrice = albumInfo.collectionPrice,
                        currency = albumInfo.currency,
                        albumTrackList = albumTrackDtoList
                    )
                }
        }

    companion object {
        private const val WRAPPER_TYPE_TRACK = "track"
    }
}
