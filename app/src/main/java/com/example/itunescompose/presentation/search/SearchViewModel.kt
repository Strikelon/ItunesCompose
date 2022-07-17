package com.example.itunescompose.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunescompose.specs.api.usecases.SearchAlbumsUseCaseApi
import com.example.itunescompose.specs.entity.AlbumInfoDto
import com.example.itunescompose.specs.entity.uistate.SearchScreenContent
import com.example.itunescompose.specs.entity.uistate.SearchScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchAlbumsUseCase: SearchAlbumsUseCaseApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenUIState())
    val uiState: StateFlow<SearchScreenUIState> = _uiState

    private var job: Job? = null

    fun searchAlbums(searchQuery: String) {
        setSearchQueryUIState(searchQuery)
        job?.cancel()
        job = viewModelScope.launch {
            delay(SEARCH_QUERY_DEBOUNCE_DELAY)
            setProgressVisibleUIState()
            searchAlbumsUseCase(searchQuery)
                .onSuccess {
                    setAlbumsContentUIState(it)
                }
                .onFailure {
                    setErrorUIState()
                }
        }
    }

    private fun setSearchQueryUIState(searchQuery: String) {
        _uiState.update { currentState: SearchScreenUIState ->
            currentState.copy(
                searchText = searchQuery
            )
        }
    }

    private fun setProgressVisibleUIState() {
        _uiState.update { currentState: SearchScreenUIState ->
            currentState.copy(
                searchScreenContent = SearchScreenContent.Progress
            )
        }
    }

    private fun setAlbumsContentUIState(albumInfoDtoList: List<AlbumInfoDto>) {
        _uiState.update { currentState: SearchScreenUIState ->
            currentState.copy(
                searchScreenContent = SearchScreenContent.Albums(albumInfoDtoList)
            )
        }
    }

    private fun setErrorUIState() {
        _uiState.update { currentState: SearchScreenUIState ->
            currentState.copy(
                searchScreenContent = SearchScreenContent.Error
            )
        }
    }

    companion object {
        private const val SEARCH_QUERY_DEBOUNCE_DELAY = 1_000L
    }
}
