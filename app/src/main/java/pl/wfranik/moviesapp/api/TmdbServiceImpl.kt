package pl.wfranik.moviesapp.api

import com.iteo.api.model.MovieDetailsDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import pl.wfranik.moviesapp.api.model.GenresResponseDTO
import pl.wfranik.moviesapp.api.model.MovieDTO
import pl.wfranik.moviesapp.api.model.PageDTO
import javax.inject.Inject

class TmdbServiceImpl @Inject constructor(
    httpClient: HttpClient
) : TmdbService {

    private val client = httpClient

    override suspend fun getPopularMoviesForDiscovery() = client
        .get("discover/movie")
        .body<PageDTO<MovieDTO>>()

    override suspend fun getFilteredMoviesForDiscovery(genreId: Int) = client
        .get("discover/movie") {
            parameter("with_genres", genreId)
        }
        .body<PageDTO<MovieDTO>>()

    override suspend fun getMovieDetails(movieId: Int) = client
        .get("movie/$movieId")
        .body<MovieDetailsDTO>()

    override suspend fun getGenres() = client
        .get("genre/movie/list")
        .body<GenresResponseDTO>()
}
