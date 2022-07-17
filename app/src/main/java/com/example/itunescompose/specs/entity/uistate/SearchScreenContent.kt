package com.example.itunescompose.specs.entity.uistate

import com.example.itunescompose.specs.entity.AlbumInfoDto

sealed class SearchScreenContent {
    class Albums(val albumInfoDtoList: List<AlbumInfoDto>) : SearchScreenContent()
    object Progress : SearchScreenContent()
    object Error : SearchScreenContent()
}
