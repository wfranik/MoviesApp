package pl.wfranik.moviesapp.api

import pl.wfranik.moviesapp.api.model.GenresResponseDTO
import pl.wfranik.moviesapp.api.model.MovieDTO
import pl.wfranik.moviesapp.api.model.PageDTO

interface TmdbService {
    suspend fun getPopularMoviesForDiscovery(): PageDTO<MovieDTO>
    suspend fun getFilteredMoviesForDiscovery(genreId: Int): PageDTO<MovieDTO>
    suspend fun getGenres(): GenresResponseDTO
}
