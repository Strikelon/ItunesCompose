package com.example.itunescompose.di

import com.example.itunescompose.core.coroutines.AppDispatcherProvider
import com.example.itunescompose.core.coroutines.DispatcherProvider
import com.example.itunescompose.data.network.datasource.AlbumRemoteDatasource
import com.example.itunescompose.data.repository.AlbumRepository
import com.example.itunescompose.domain.usecases.GetAlbumTracksByIdUseCase
import com.example.itunescompose.domain.usecases.SearchAlbumsUseCase
import com.example.itunescompose.specs.api.repository.AlbumRepositoryApi
import com.example.itunescompose.specs.api.usecases.GetAlbumTracksByIdUseCaseApi
import com.example.itunescompose.specs.api.usecases.SearchAlbumsUseCaseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = AppDispatcherProvider()

    @Provides
    @Singleton
    fun provideAlbumRepository(
        albumRemoteDatasource: AlbumRemoteDatasource,
        dispatcherProvider: DispatcherProvider
    ): AlbumRepositoryApi =
        AlbumRepository(
            albumRemoteDatasource,
            dispatcherProvider
        )

    @Provides
    fun provideSearchAlbumsUseCase(
        albumRepository: AlbumRepositoryApi
    ): SearchAlbumsUseCaseApi =
        SearchAlbumsUseCase(
            albumRepository
        )

    @Provides
    fun provideGetAlbumTracksByIdUseCase(
        albumRepository: AlbumRepositoryApi
    ): GetAlbumTracksByIdUseCaseApi =
        GetAlbumTracksByIdUseCase(
            albumRepository
        )
}
