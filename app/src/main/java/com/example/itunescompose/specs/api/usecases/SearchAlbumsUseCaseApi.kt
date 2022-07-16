package com.example.itunescompose.specs.api.usecases

import com.example.itunescompose.specs.entity.AlbumInfoDto

interface SearchAlbumsUseCaseApi {

     suspend operator fun invoke(term: String): Result<List<AlbumInfoDto>>
}