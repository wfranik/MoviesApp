package pl.wfranik.moviesapp.domain

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import pl.wfranik.data_contract.MoviesRepository
import pl.wfranik.domain_models.Genre
import pl.wfranik.domain_models.Movie
import pl.wfranik.domain_models.MovieDetails
import pl.wfranik.domain_models.MovieWithDetails
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
