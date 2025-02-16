package pl.wfranik.moviesapp.data.genre

import kotlinx.coroutines.flow.Flow
import pl.wfranik.domain_models.Genre
import pl.wfranik.moviesapp.api.TmdbService
import pl.wfranik.moviesapp.data.movies.GenreInMemoryStore
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val tmdbService: TmdbService,
    private val genreInMemoryStore: GenreInMemoryStore
) : GenresRepository {

    override suspend fun getGenres(): Result<List<Genre>> = runCatching {
        tmdbService.getGenres().genres.map { genre ->
            Genre(
                id = genre.id,
                name = genre.name
            )
        }
    }

    override suspend fun saveGenre(genre: Genre): Result<Unit> = runCatching {
        genreInMemoryStore.setSelectedGenre(genre)
    }

    override fun observeSelectedGenre(): Flow<Genre> =
        genreInMemoryStore.observeSelectedGenre()

    override suspend fun clearSelectedGenre(): Result<Unit> = runCatching {
        genreInMemoryStore.clearSelectedGenre()
    }

    override fun getSelectedGenre(): Genre = genreInMemoryStore.getSelectedGenre()

}
