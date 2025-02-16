package pl.wfranik.domain_usecase

import kotlinx.coroutines.flow.Flow
import pl.wfranik.data_contract.GenresRepository
import pl.wfranik.domain_models.Genre
import javax.inject.Inject

class ObserveSelectedGenreUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {

    operator fun invoke(): Flow<Genre> =
        genresRepository.observeSelectedGenre()
}
