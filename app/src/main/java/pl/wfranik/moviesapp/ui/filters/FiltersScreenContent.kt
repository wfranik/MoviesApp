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
import pl.wfranik.moviesapp.R
import pl.wfranik.moviesapp.domain.model.Genre
import pl.wfranik.moviesapp.ui.common.components.EmptyListContent
import pl.wfranik.moviesapp.ui.common.components.ErrorContent
import pl.wfranik.moviesapp.ui.common.components.ErrorSnackbarHost
import pl.wfranik.moviesapp.ui.common.components.LoadingContent
import pl.wfranik.moviesapp.ui.common.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.common.theme.MoviesAppTheme
import pl.wfranik.moviesapp.ui.common.utils.TextLabel
import pl.wfranik.moviesapp.ui.common.utils.getText
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
    onGenreClick: (Genre) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(genres) { genre ->
            GenreItem(genre, onGenreClick)
        }
    }
}

@Composable
fun GenreItem(
    genre: Genre,
    onGenreClick: (Genre) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.padding(8.dp),
        onClick = { onGenreClick(genre) },
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = genre.name.getText(),
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
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
            genre = Genre(1, TextLabel("Action")),
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
                Genre(1, TextLabel("Action")),
                Genre(2, TextLabel("Comedy")),
                Genre(3, TextLabel("Drama")),
                Genre(4, TextLabel("Horror")),
                Genre(5, TextLabel("Thriller")),
                Genre(6, TextLabel("Sci-Fi")),
                Genre(7, TextLabel("Fantasy")),
                Genre(8, TextLabel("Adventure")),
                Genre(9, TextLabel("Animation")),
                Genre(10, TextLabel("Family")),
                Genre(11, TextLabel("Romance")),
                Genre(12, TextLabel("Mystery")),
                Genre(13, TextLabel("Crime")),
                Genre(14, TextLabel("Documentary")),
                Genre(15, TextLabel("Music")),
                Genre(16, TextLabel("History")),
                Genre(17, TextLabel("War")),
                Genre(18, TextLabel("Western")),
                Genre(19, TextLabel("TV Movie"))
            ),
            onGenreClick = { }
        )
    }
}
