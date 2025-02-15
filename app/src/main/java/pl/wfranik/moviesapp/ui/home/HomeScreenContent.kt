package pl.wfranik.moviesapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.wfranik.moviesapp.R
import pl.wfranik.moviesapp.domain.model.Movie
import pl.wfranik.moviesapp.ui.common.components.AsyncImage
import pl.wfranik.moviesapp.ui.common.components.EmptyListContent
import pl.wfranik.moviesapp.ui.common.components.ErrorContent
import pl.wfranik.moviesapp.ui.common.components.ErrorSnackbarHost
import pl.wfranik.moviesapp.ui.common.components.ImagePathBuilder
import pl.wfranik.moviesapp.ui.common.components.LoadingContent
import pl.wfranik.moviesapp.ui.common.components.PlaceholderComponent
import pl.wfranik.moviesapp.ui.common.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.common.theme.MoviesAppTheme
import pl.wfranik.moviesapp.ui.home.HomeViewAction.OnRetryClicked

@Composable
internal fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeViewState,
    snackbarHostState: SnackbarHostState,
    onViewAction: (HomeViewAction) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            ErrorSnackbarHost(snackbarHostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onViewAction(HomeViewAction.OnChangeFiltersClicked) },
                icon = { Icon(painter = painterResource(id = R.drawable.round_filter_list_24), contentDescription = "Filter") },
                text = { Text(text = "Filter") },
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            when {
                state.isLoading -> LoadingContent()

                state.isError -> ErrorContent(
                    onRetry = { onViewAction(OnRetryClicked) }
                )

                state.movies.isEmpty() -> EmptyListContent()

                else -> MoviesList(
                    movies = state.movies,
                    onMovieClick = { onViewAction(HomeViewAction.OnMovieClicked(it)) }
                )
            }
        }
    }
}

@Composable
fun MoviesList(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie, onMovieClick)
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onMovieClick: (Movie) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.padding(8.dp),
        onClick = { onMovieClick(movie) }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                imageUrl = ImagePathBuilder()
                    .withResource(movie.imageUrl)
                    .withSize(ImagePathBuilder.BackdropSize.W1280).build(),
                contentScale = ContentScale.Crop,
                loadingContent = {
                    PlaceholderComponent()
                }
            )
            Text(
                text = movie.title,
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@DefaultPreviews
@Composable
private fun PreviewGenreItem() {
    MoviesAppTheme {
        MovieItem(
            movie = Movie(
                id = 1,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
            ),
            onMovieClick = { }
        )
    }
}

@DefaultPreviews
@Composable
private fun PreviewGenresList() {
    MoviesAppTheme {
        MoviesList(
            movies = listOf(
                Movie(
                    id = 1,
                    title = "Movie Title",
                    imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                ),
                Movie(
                    id = 2,
                    title = "Movie Title",
                    imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                ),
                Movie(
                    id = 3,
                    title = "Movie Title",
                    imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                ),
                Movie(
                    id = 4,
                    title = "Movie Title",
                    imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                ),
                Movie(
                    id = 5,
                    title = "Movie Title",
                    imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                ),
                Movie(
                    id = 6,
                    title = "Movie Title",
                    imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                ),
            ),
            onMovieClick = { }
        )
    }
}
