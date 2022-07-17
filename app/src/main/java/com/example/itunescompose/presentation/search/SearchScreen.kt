package com.example.itunescompose.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.itunescompose.R
import com.example.itunescompose.specs.entity.AlbumInfoDto
import com.example.itunescompose.specs.entity.uistate.SearchScreenContent

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel,
    onAlbumSelected: (String) -> Unit
) {
    val screenState = searchViewModel.uiState.collectAsState()
    val searchScreenContent = screenState.value.searchScreenContent

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchAlbumTextField(
            value = screenState.value.searchText,
            onValueChange = { searchQuery: String ->
                searchViewModel.searchAlbums(searchQuery)
            }
        )
        when(searchScreenContent) {
            is SearchScreenContent.Progress -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.circular_progress_indicator_padding_top)
                    )
                )
            }
            is SearchScreenContent.Error -> {
                Text(
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.search_error_padding_top),
                    ),
                    text = stringResource(R.string.search_error),
                    color = MaterialTheme.colors.error
                )
            }
            is SearchScreenContent.Albums -> {
                AlbumList(
                    list = searchScreenContent.albumInfoDtoList,
                    onClick = { onAlbumSelected(it) }
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAlbumTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.search_album_text_field_horizontal_padding))
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            leadingIconColor = MaterialTheme.colors.primary
        ),
        singleLine = true,
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_title)) },
        placeholder = { Text(text = stringResource(R.string.search_title)) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {keyboardController?.hide()})
    )
}

@Composable
fun AlbumList(
    modifier: Modifier = Modifier,
    list: List<AlbumInfoDto>,
    onClick: (String) -> Unit = {},
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(dimensionResource(R.dimen.album_item_padding))
    ) {
        items(
            items = list,
            key = { albumInfoDto -> albumInfoDto.collectionId }
        ) { albumInfoDto ->
            AlbumItem(
                albumName = albumInfoDto.collectionName,
                albumImage = albumInfoDto.artworkUrl100,
                onClick = {
                    onClick(albumInfoDto.collectionId)
                }
            )
        }
    }
}

@Composable
fun AlbumItem(
    modifier: Modifier = Modifier,
    albumName: String,
    albumImage: String,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = albumImage,
            contentDescription = albumName,
            modifier = modifier.size(dimensionResource(R.dimen.album_item_image_size))
        )
        Text(
            text = albumName,
            modifier = modifier.padding(dimensionResource(R.dimen.album_item_image_text_top_padding)),
            textAlign = TextAlign.Center
        )
    }
}