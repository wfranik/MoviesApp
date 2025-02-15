package pl.wfranik.moviesapp.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
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

    override suspend fun getGenres() = client
        .get("genre/movie/list")
        .body<GenresResponseDTO>()
}
