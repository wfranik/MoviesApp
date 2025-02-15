package pl.wfranik.moviesapp.api

import pl.wfranik.moviesapp.api.model.MovieDTO
import pl.wfranik.moviesapp.api.model.PageDTO
import pl.wfranik.moviesapp.api.model.GenreDTO

interface TmdbService {
    suspend fun getPopularMoviesForDiscovery(): PageDTO<MovieDTO>
    suspend fun getGenres(): PageDTO<GenreDTO>
}
