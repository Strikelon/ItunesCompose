package com.example.itunescompose.specs.entity.uistate

data class SearchScreenUIState(
    val searchText: String = "",
    val searchScreenContent: SearchScreenContent = SearchScreenContent.Albums(listOf())
)
