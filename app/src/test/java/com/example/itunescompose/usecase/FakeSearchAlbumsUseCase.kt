package com.example.itunescompose.usecase

import com.example.itunescompose.model.Models
import com.example.itunescompose.specs.api.usecases.SearchAlbumsUseCaseApi
import com.example.itunescompose.specs.entity.AlbumInfoDto
import kotlinx.coroutines.delay

class FakeSearchAlbumsUseCase(
    private val isError: Boolean = false
) : SearchAlbumsUseCaseApi {

    override suspend fun invoke(term: String): Result<List<AlbumInfoDto>> {
        delay(REQUEST_DELAY)
        return if (isError) {
            Result.failure(Throwable("test throwable"))
        } else {
            Result.success(Models.albumInfoDtoList)
        }
    }

    companion object {
        private const val REQUEST_DELAY = 2_000L
    }
}
