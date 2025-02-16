package pl.wfranik.moviesapp.domain

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import pl.wfranik.moviesapp.data.movies.MoviesRepository
import pl.wfranik.moviesapp.domain.model.Genre
import pl.wfranik.moviesapp.domain.model.Movie
import pl.wfranik.moviesapp.domain.model.MovieDetails
import pl.wfranik.moviesapp.domain.model.MovieWithDetails
import javax.inject.Inject

class LoadMoviesWithDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(selectedGenre: Genre): List<MovieWithDetails> = withContext(IO) {
        val movies = fetchMoviesFromApi(selectedGenre)

        val detailsDeferred = movies.map { movie ->
            async {
                MovieWithDetails(
                    movie = movie,
                    movieDetails = fetchMovieDetails(movie)
                )
            }
        }

        detailsDeferred.awaitAll()
    }

    private suspend fun fetchMoviesFromApi(selectedGenre: Genre): List<Movie> {
        return if (selectedGenre.id == Genre.DEFAULT.id) {
            moviesRepository.getMovies().getOrThrow()
        } else {
            moviesRepository.getFilteredMovies(selectedGenre).getOrThrow()
        }
    }

    private suspend fun fetchMovieDetails(movie: Movie): MovieDetails {
        return moviesRepository.getMovieDetails(movie).getOrThrow()
    }
}
