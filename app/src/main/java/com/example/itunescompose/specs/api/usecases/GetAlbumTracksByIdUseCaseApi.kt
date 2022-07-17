package com.example.itunescompose.specs.api.usecases

import com.example.itunescompose.specs.entity.AlbumTrackInfoDto

interface GetAlbumTracksByIdUseCaseApi {
    suspend operator fun invoke(id: String): Result<AlbumTrackInfoDto>
}
