package com.example.itunescompose.presentation

import com.example.itunescompose.model.Models
import com.example.itunescompose.presentation.search.SearchViewModel
import com.example.itunescompose.specs.entity.uistate.SearchScreenContent
import com.example.itunescompose.usecase.FakeSearchAlbumsUseCase
import com.example.itunescompose.util.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Test
    fun `search query success`() {
        val viewModel = SearchViewModel(
            FakeSearchAlbumsUseCase()
        )
        viewModel.searchAlbums("Kiss")
        coroutineRule.testDispatcher.scheduler.advanceTimeBy(1_100L)
        val actualStateContent = viewModel.uiState.value.searchScreenContent
        val searchText = viewModel.uiState.value.searchText
        assertTrue(actualStateContent is SearchScreenContent.Progress)
        assertEquals("Kiss", searchText)
        coroutineRule.testDispatcher.scheduler.advanceTimeBy(2_000L)
        val actualStateContent2 = viewModel.uiState.value.searchScreenContent
        assertTrue(actualStateContent2 is SearchScreenContent.Albums)
        if (viewModel.uiState.value.searchScreenContent is SearchScreenContent.Albums) {
            val actualStateContent2Albums = (viewModel.uiState.value.searchScreenContent as SearchScreenContent.Albums).albumInfoDtoList
            assertEquals(Models.albumInfoDtoList, actualStateContent2Albums)
        }
    }

    @Test
    fun `search query error`() {
        val viewModel = SearchViewModel(
            FakeSearchAlbumsUseCase(isError = true)
        )
        viewModel.searchAlbums("Kiss")
        coroutineRule.testDispatcher.scheduler.advanceTimeBy(1_100L)
        val actualStateContent = viewModel.uiState.value.searchScreenContent
        val searchText = viewModel.uiState.value.searchText
        assertTrue(actualStateContent is SearchScreenContent.Progress)
        assertEquals("Kiss", searchText)
        coroutineRule.testDispatcher.scheduler.advanceTimeBy(2_000L)
        val actualStateContent2 = viewModel.uiState.value.searchScreenContent
        assertTrue(actualStateContent2 is SearchScreenContent.Error)
    }
}
