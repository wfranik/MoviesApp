package pl.wfranik.moviesapp.ui.filters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.wfranik.moviesapp.domain.model.Genre
import pl.wfranik.moviesapp.ui.common.components.EmptyListContent
import pl.wfranik.moviesapp.ui.common.components.ErrorContent
import pl.wfranik.moviesapp.ui.common.components.ErrorSnackbarHost
import pl.wfranik.moviesapp.ui.common.components.LoadingContent
import pl.wfranik.moviesapp.ui.common.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.common.theme.MoviesAppTheme
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
                text = genre.name,
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
private fun PreviewGenreItem() {
    MoviesAppTheme {
        GenreItem(
            genre = Genre(1, "Action"),
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
            onGenreClick = { }
        )
    }
}
