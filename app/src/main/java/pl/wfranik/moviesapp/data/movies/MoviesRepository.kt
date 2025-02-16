package pl.wfranik.moviesapp.data.movies

import pl.wfranik.moviesapp.domain.model.Genre
import pl.wfranik.moviesapp.domain.model.Movie
import pl.wfranik.moviesapp.domain.model.MovieDetails

interface MoviesRepository {
    suspend fun getMovies(): Result<List<Movie>>
    suspend fun getFilteredMovies(genre: Genre): Result<List<Movie>>
    suspend fun getMovieDetails(movie: Movie): Result<MovieDetails>
}
