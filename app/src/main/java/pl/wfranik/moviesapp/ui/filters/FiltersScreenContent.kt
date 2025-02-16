package pl.wfranik.moviesapp.ui.filters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.wfranik.domain_models.Genre
import pl.wfranik.moviesapp.R
import pl.wfranik.moviesapp.ui.common.components.EmptyListContent
import pl.wfranik.moviesapp.ui.common.components.ErrorContent
import pl.wfranik.moviesapp.ui.common.components.ErrorSnackbarHost
import pl.wfranik.moviesapp.ui.common.components.LoadingContent
import pl.wfranik.moviesapp.ui.common.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.common.theme.MoviesAppTheme
import pl.wfranik.moviesapp.ui.filters.FiltersViewAction.OnBackClicked
import pl.wfranik.moviesapp.ui.filters.FiltersViewAction.OnRetryClicked

@Composable
internal fun FiltersScreenContent(
    modifier: Modifier = Modifier,
    state: FiltersViewState,
    snackbarHostState: SnackbarHostState,
    onViewAction: (FiltersViewAction) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            ErrorSnackbarHost(snackbarHostState = snackbarHostState)
        },
        topBar = {
            FiltersTopAppBar(onViewAction = onViewAction)
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            when {
                state.isLoading -> LoadingContent()

                state.isError -> ErrorContent(
                    onRetry = { onViewAction(OnRetryClicked) }
                )

                state.genres.isEmpty() -> EmptyListContent()

                else -> GenresList(
                    genres = state.genres,
                    selectedGenre = state.selectedGenre,
                    onGenreClick = { onViewAction(FiltersViewAction.OnGenreClicked(it)) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersTopAppBar(
    modifier: Modifier = Modifier,
    onViewAction: (FiltersViewAction) -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.genres_screen_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(onClick = { onViewAction(OnBackClicked) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
fun GenresList(
    genres: List<Genre>,
    selectedGenre: Genre,
    onGenreClick: (Genre) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(genres) { genre ->
            GenreItem(genre, selectedGenre, onGenreClick)
        }
    }
}

@Composable
fun GenreItem(
    genre: Genre,
    selectedGenre: Genre,
    onGenreClick: (Genre) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.padding(8.dp),
        onClick = { onGenreClick(genre) },
        colors = if (genre == selectedGenre) {
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        } else {
            CardDefaults.cardColors()
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = if (genre == Genre.DEFAULT) {
                    stringResource(R.string.genre_default_option_label)
                } else {
                    genre.name
                },
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@DefaultPreviews
@Composable
private fun PreviewTopAppBar() {
    MoviesAppTheme {
        FiltersTopAppBar {}
    }
}

@DefaultPreviews
@Composable
private fun PreviewGenreItem() {
    MoviesAppTheme {
        GenreItem(
            genre = Genre(1, "Action"),
            selectedGenre = Genre.DEFAULT,
            onGenreClick = { }
        )
    }
}

@DefaultPreviews
@Composable
private fun PreviewGenresList() {
    MoviesAppTheme {
        GenresList(
            genres = listOf(
                Genre.DEFAULT,
                Genre(1, "Action"),
                Genre(2, "Comedy"),
                Genre(3, "Drama"),
                Genre(4, "Horror"),
                Genre(5, "Thriller"),
                Genre(6, "Sci-Fi"),
                Genre(7, "Fantasy"),
                Genre(8, "Adventure"),
                Genre(9, "Animation"),
                Genre(10, "Family"),
                Genre(11, "Romance"),
                Genre(12, "Mystery"),
                Genre(13, "Crime"),
                Genre(14, "Documentary"),
                Genre(15, "Music"),
                Genre(16, "History"),
                Genre(17, "War"),
                Genre(18, "Western"),
                Genre(19, "TV Movie")
            ),
            selectedGenre = Genre.DEFAULT,
            onGenreClick = { }
        )
    }
}
