package pl.wfranik.data_contract

import kotlinx.coroutines.flow.Flow
import pl.wfranik.domain_models.Genre

interface GenresRepository {
    suspend fun getGenres(): Result<List<Genre>>
    suspend fun saveGenre(genre: Genre): Result<Unit>
    fun observeSelectedGenre(): Flow<Genre>
    suspend fun clearSelectedGenre(): Result<Unit>
    fun getSelectedGenre(): Genre
}
