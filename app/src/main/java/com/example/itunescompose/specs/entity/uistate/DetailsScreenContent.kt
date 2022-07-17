package com.example.itunescompose.specs.entity.uistate

import com.example.itunescompose.specs.entity.AlbumTrackInfoDto

sealed class DetailsScreenContent {
    object Progress : DetailsScreenContent()
    object Error : DetailsScreenContent()
    class AlbumTrackInfo(val albumTrackInfoDto: AlbumTrackInfoDto) : DetailsScreenContent()
}
