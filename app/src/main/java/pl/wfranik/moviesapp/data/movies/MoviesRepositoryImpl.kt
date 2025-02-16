package pl.wfranik.moviesapp.data.movies

import pl.wfranik.domain_models.Genre
import pl.wfranik.domain_models.Movie
import pl.wfranik.domain_models.MovieDetails
import pl.wfranik.moviesapp.api.TmdbService
import pl.wfranik.moviesapp.data.mapper.MovieDTOMapper
import pl.wfranik.moviesapp.data.mapper.MovieDetailsDTOMapper
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val tmdbService: TmdbService,
    private val movieDTOMapper: MovieDTOMapper,
    private val movieDetailsDTOMapper: MovieDetailsDTOMapper
) : MoviesRepository {

    override suspend fun getMovies(): Result<List<Movie>> = runCatching {
        tmdbService.getPopularMoviesForDiscovery().resultList.map { movieDTO ->
            movieDTOMapper(movieDTO)
        }
    }

    override suspend fun getFilteredMovies(genre: Genre): Result<List<Movie>> = runCatching {
        tmdbService.getFilteredMoviesForDiscovery(genreId = genre.id).resultList.map { movieDTO ->
            movieDTOMapper(movieDTO)
        }
    }

    override suspend fun getMovieDetails(movie: Movie): Result<MovieDetails> = runCatching {
        tmdbService.getMovieDetails(movieId = movie.id).let { movieDetailsDTO ->
            movieDetailsDTOMapper(movieDetailsDTO)
        }
    }
}
