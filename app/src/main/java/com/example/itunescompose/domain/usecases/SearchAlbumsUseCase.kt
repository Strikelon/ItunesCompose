package com.example.itunescompose.domain.usecases

import com.example.itunescompose.specs.api.repository.AlbumRepositoryApi
import com.example.itunescompose.specs.api.usecases.SearchAlbumsUseCaseApi
import com.example.itunescompose.specs.entity.AlbumInfoDto
import javax.inject.Inject

class SearchAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepositoryApi
): SearchAlbumsUseCaseApi {

    override suspend fun invoke(term: String): Result<List<AlbumInfoDto>> =
        albumRepository.getAlbumsInfoList(term)
}