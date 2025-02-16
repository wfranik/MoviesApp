package pl.wfranik.data_contract

import pl.wfranik.domain_models.Genre
import pl.wfranik.domain_models.Movie
import pl.wfranik.domain_models.MovieDetails

interface MoviesRepository {
    suspend fun getMovies(): Result<List<Movie>>
    suspend fun getFilteredMovies(genre: Genre): Result<List<Movie>>
    suspend fun getMovieDetails(movie: Movie): Result<MovieDetails>
}
