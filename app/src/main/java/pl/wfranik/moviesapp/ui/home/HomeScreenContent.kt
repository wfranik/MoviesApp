package pl.wfranik.moviesapp.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.wfranik.moviesapp.R
import pl.wfranik.moviesapp.ui.common.components.AsyncImage
import pl.wfranik.moviesapp.ui.common.components.EmptyListContent
import pl.wfranik.moviesapp.ui.common.components.ErrorContent
import pl.wfranik.moviesapp.ui.common.components.ErrorSnackbarHost
import pl.wfranik.moviesapp.ui.common.components.LoadingContent
import pl.wfranik.moviesapp.ui.common.components.PlaceholderComponent
import pl.wfranik.moviesapp.ui.common.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.common.theme.MoviesAppTheme
import pl.wfranik.moviesapp.ui.home.HomeViewAction.OnRetryClicked
import pl.wfranik.moviesapp.ui.home.model.MovieListItem

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
    movies: List<MovieListItem>,
    onMovieClick: (MovieListItem) -> Unit
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
    movie: MovieListItem,
    onMovieClick: (MovieListItem) -> Unit
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
                imageUrl = movie.imageUrl,
                contentScale = ContentScale.Crop,
                loadingContent = {
                    PlaceholderComponent()
                }
            )
            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Text(
                    text = movie.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(4.dp))

                listOf(
                    R.string.movie_rating_label to movie.rating.toString(),
                    R.string.movie_revenue_label to movie.revenue,
                    R.string.movie_budget_label to movie.budget,
                ).forEach { (labelResId, value) ->
                    MovieInfoLabel(
                        labelResId = labelResId,
                        value = value
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieInfoLabel(
    modifier: Modifier = Modifier,
    @StringRes labelResId: Int,
    value: String
) {
    Text(
        modifier = modifier,
        text = stringResource(labelResId, value),
        fontSize = 14.sp,
    )
}

@DefaultPreviews
@Composable
private fun PreviewGenreItem() {
    MoviesAppTheme {
        MovieItem(
            movie = MovieListItem.previewMovieListItem,
            onMovieClick = { }
        )
    }
}

@DefaultPreviews
@Composable
private fun PreviewGenresList() {
    MoviesAppTheme {
        MoviesList(
            movies = MovieListItem.previewMovieListItemList,
            onMovieClick = { }
        )
    }
}
