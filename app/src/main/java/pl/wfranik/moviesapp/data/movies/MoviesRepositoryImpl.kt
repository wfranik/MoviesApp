package pl.wfranik.moviesapp.data.movies

import pl.wfranik.moviesapp.api.TmdbService
import pl.wfranik.moviesapp.domain.model.Genre
import pl.wfranik.moviesapp.domain.model.Movie
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val tmdbService: TmdbService
) : MoviesRepository {

    override suspend fun getMovies(): Result<List<Movie>> = runCatching {
        tmdbService.getPopularMoviesForDiscovery().resultList.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                imageUrl = movie.posterResourceName ?: ""
            )
        }
    }

    override suspend fun getFilteredMovies(genre: Genre): Result<List<Movie>> = runCatching {
        tmdbService.getFilteredMoviesForDiscovery(genreId = genre.id).resultList.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                imageUrl = movie.posterResourceName ?: ""
            )
        }
    }
}
