package com.example.itunescompose.domain.usecases

import com.example.itunescompose.specs.api.repository.AlbumRepositoryApi
import com.example.itunescompose.specs.api.usecases.GetAlbumTracksByIdUseCaseApi
import com.example.itunescompose.specs.entity.AlbumTrackInfoDto
import javax.inject.Inject

class GetAlbumTracksByIdUseCase @Inject constructor(
    private val albumRepository: AlbumRepositoryApi
): GetAlbumTracksByIdUseCaseApi {

    override suspend fun invoke(id: String): Result<AlbumTrackInfoDto> =
        albumRepository.getAlbumTracksById(id)
}