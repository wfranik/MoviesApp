package pl.wfranik.moviesapp.data

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

    override suspend fun getGenres(): Result<List<Genre>> = runCatching {
        tmdbService.getGenres().resultList.map { genre ->
            Genre(
                id = genre.id,
                name = genre.name
            )
        }
    }
}
