package com.example.itunescompose.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.itunescompose.R
import com.example.itunescompose.presentation.theme.Gray
import com.example.itunescompose.specs.entity.AlbumTrackDto
import com.example.itunescompose.specs.entity.AlbumTrackInfoDto
import com.example.itunescompose.specs.entity.uistate.DetailsScreenContent

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailsViewModel: DetailsViewModel
) {
    val detailsState = detailsViewModel.uiState.collectAsState()
    val detailsScreenContent = detailsState.value.detailsScreenContent
    when (detailsScreenContent) {
        is DetailsScreenContent.Progress -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is DetailsScreenContent.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.details_error),
                    color = MaterialTheme.colors.error
                )
            }
        }
        is DetailsScreenContent.AlbumTrackInfo -> {
            val albumTrackInfoDto = detailsScreenContent.albumTrackInfoDto
            DetailsInfo(albumTrackInfoDto = albumTrackInfoDto)
        }
    }
}

@Composable
fun DetailsInfo(
    modifier: Modifier = Modifier,
    albumTrackInfoDto: AlbumTrackInfoDto
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            AlbumDescription(
                collectionName = albumTrackInfoDto.collectionName,
                artistName = albumTrackInfoDto.artistName,
                primaryGenreName = albumTrackInfoDto.primaryGenreName,
                artworkUrl100 = albumTrackInfoDto.artworkUrl100,
                trackCount = albumTrackInfoDto.trackCount,
                collectionPrice = albumTrackInfoDto.collectionPrice,
                currency = albumTrackInfoDto.currency
            )
        }
        items(
            items = albumTrackInfoDto.albumTrackList,
        ) { albumTrackDto ->
            AlbumTrack(albumTrackDto = albumTrackDto)
        }
    }
}

@Composable
fun AlbumDescription(
    modifier: Modifier = Modifier,
    collectionName: String,
    artistName: String,
    primaryGenreName: String,
    artworkUrl100: String,
    trackCount: String,
    collectionPrice: String,
    currency: String
) {
    Column(
        modifier = modifier.padding(start = dimensionResource(R.dimen.album_description_padding_start)),
    ) {
        Text(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.album_description_padding_top)),
            text = collectionName,
            style = MaterialTheme.typography.h6
        )
        Text(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.album_description_padding_top)),
            text = artistName,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.album_description_padding_top)),
            text = primaryGenreName,
            color = Gray
        )
        AsyncImage(
            model = artworkUrl100,
            contentDescription = collectionName,
            modifier = modifier
                .padding(top = dimensionResource(R.dimen.album_description_padding_top))
                .size(dimensionResource(R.dimen.album_description_image_size))
        )
        Text(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.album_description_padding_top)),
            text = "$trackCount songs - $collectionPrice $currency",
            color = Gray
        )
    }
}

@Composable
fun AlbumTrack(
    modifier: Modifier = Modifier,
    albumTrackDto: AlbumTrackDto
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.album_track_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_song),
            contentDescription = albumTrackDto.trackName,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.album_track_image_padding))
                .size(dimensionResource(R.dimen.album_track_image_size))
        )
        Text(
            text = "${albumTrackDto.trackName} - ${albumTrackDto.trackPrice} ${albumTrackDto.currency}"
        )
    }
}
