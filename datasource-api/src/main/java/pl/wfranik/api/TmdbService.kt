package pl.wfranik.api

import pl.wfranik.api.model.MovieDetailsDTO
import pl.wfranik.api.model.GenresResponseDTO
import pl.wfranik.api.model.MovieDTO
import pl.wfranik.api.model.PageDTO

interface TmdbService {
    suspend fun getPopularMoviesForDiscovery(): PageDTO<MovieDTO>
    suspend fun getFilteredMoviesForDiscovery(genreId: Int): PageDTO<MovieDTO>
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDTO
    suspend fun getGenres(): GenresResponseDTO
}
