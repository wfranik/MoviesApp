package pl.wfranik.moviesapp.data

import pl.wfranik.moviesapp.domain.model.Genre
import pl.wfranik.moviesapp.domain.model.Movie

interface MoviesRepository {
    suspend fun getMovies(): Result<List<Movie>>
    suspend fun getGenres(): Result<List<Genre>>
}
