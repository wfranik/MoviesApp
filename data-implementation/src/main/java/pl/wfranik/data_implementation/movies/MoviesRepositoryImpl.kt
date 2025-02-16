package pl.wfranik.data_implementation.movies

import pl.wfranik.data_contract.MoviesRepository
import pl.wfranik.data_implementation.mapper.MovieDTOMapper
import pl.wfranik.data_implementation.mapper.MovieDetailsDTOMapper
import pl.wfranik.datasource_api_contract.TmdbService
import pl.wfranik.domain_models.Genre
import pl.wfranik.domain_models.Movie
import pl.wfranik.domain_models.MovieDetails
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
        movieDetailsDTOMapper(tmdbService.getMovieDetails(movieId = movie.id))
    }
}
