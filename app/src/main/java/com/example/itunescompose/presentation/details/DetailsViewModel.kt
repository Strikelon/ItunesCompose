package com.example.itunescompose.presentation.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunescompose.presentation.MainActivity.Companion.ALBUM_ID_ARG
import com.example.itunescompose.specs.api.usecases.GetAlbumTracksByIdUseCaseApi
import com.example.itunescompose.specs.entity.AlbumTrackInfoDto
import com.example.itunescompose.specs.entity.uistate.DetailsScreenContent
import com.example.itunescompose.specs.entity.uistate.DetailsScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getAlbumTracksByIdUseCaseApi: GetAlbumTracksByIdUseCaseApi,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsScreenUIState())
    val uiState: StateFlow<DetailsScreenUIState> = _uiState

    private val albumId: String? by lazy {
        savedStateHandle.get<String>(
            ALBUM_ID_ARG
        )
    }

    init {
        Log.i("DebugLog", "albumId $albumId")
        albumId?.let { albumIdNotNull ->
            viewModelScope.launch {
                getAlbumTracksByIdUseCaseApi(albumIdNotNull)
                    .onSuccess {
                        Log.i("DebugLog", "getAlbumTracksByIdUseCaseApi success $it")
                        setAlbumTrackInfoUIState(it)
                    }
                    .onFailure {
                        Log.i("DebugLog", "getAlbumTracksByIdUseCaseApi error $it")
                        setErrorUIState()
                    }
            }
        } ?: setErrorUIState()
    }

    private fun setErrorUIState() {
        _uiState.update { currentState: DetailsScreenUIState ->
            currentState.copy(
                detailsScreenContent = DetailsScreenContent.Error
            )
        }
    }

    private fun setAlbumTrackInfoUIState(albumTrackInfoDto: AlbumTrackInfoDto) {
        _uiState.update { currentState: DetailsScreenUIState ->
            currentState.copy(
                detailsScreenContent = DetailsScreenContent.AlbumTrackInfo(albumTrackInfoDto)
            )
        }
    }
}
