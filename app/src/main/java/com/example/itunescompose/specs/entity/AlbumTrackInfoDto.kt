package com.example.itunescompose.specs.entity

data class AlbumTrackInfoDto(
    val artistName: String,
    val collectionName: String,
    val primaryGenreName: String,
    val artworkUrl100: String,
    val trackCount: String,
    val collectionPrice: String,
    val currency: String,
    val albumTrackList: List<AlbumTrackDto>
)
