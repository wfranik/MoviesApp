package pl.wfranik.moviesapp.api

import com.iteo.api.model.MovieDetailsDTO
import pl.wfranik.moviesapp.api.model.GenresResponseDTO
import pl.wfranik.moviesapp.api.model.MovieDTO
import pl.wfranik.moviesapp.api.model.PageDTO

interface TmdbService {
    suspend fun getPopularMoviesForDiscovery(): PageDTO<MovieDTO>
    suspend fun getFilteredMoviesForDiscovery(genreId: Int): PageDTO<MovieDTO>
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDTO
    suspend fun getGenres(): GenresResponseDTO
}
