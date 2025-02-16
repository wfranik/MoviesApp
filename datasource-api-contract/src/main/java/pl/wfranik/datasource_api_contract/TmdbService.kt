package pl.wfranik.datasource_api_contract

import pl.wfranik.datasource_api_models.GenresResponseDTO
import pl.wfranik.datasource_api_models.MovieDTO
import pl.wfranik.datasource_api_models.MovieDetailsDTO
import pl.wfranik.datasource_api_models.PageDTO

interface TmdbService {
    suspend fun getPopularMoviesForDiscovery(): PageDTO<MovieDTO>
    suspend fun getFilteredMoviesForDiscovery(genreId: Int): PageDTO<MovieDTO>
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDTO
    suspend fun getGenres(): GenresResponseDTO
}
