package com.example.itunescompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.itunescompose.presentation.search.SearchScreen
import com.example.itunescompose.presentation.search.SearchViewModel
import com.example.itunescompose.presentation.theme.ItunesComposeTheme
import com.example.itunescompose.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItunesComposeTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(R.string.app_name))
                            }
                        )
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        NavHost(navController = navController, startDestination = SEARCH_SCREEN) {
                            composable(SEARCH_SCREEN) {
                                val searchViewModel = hiltViewModel<SearchViewModel>()
                                SearchScreen(searchViewModel = searchViewModel) { albumId: String ->
                                    navController.navigate("$DETAILS_SCREEN/$albumId")
                                }
                            }
                            composable(
                                "$DETAILS_SCREEN/{$ALBUM_ID_ARG}",
                                arguments = listOf(
                                    navArgument(ALBUM_ID_ARG) {
                                        type = NavType.StringType
                                    },
                                ),
                            ) { entry ->
                                val albumId = entry.arguments?.getString(ALBUM_ID_ARG)
                                Text(
                                    text = "Details screen id = $albumId"
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val SEARCH_SCREEN = "search"
        private const val DETAILS_SCREEN = "details"
        private const val ALBUM_ID_ARG = "album_id_arg"
    }
}