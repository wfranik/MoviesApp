package pl.wfranik.domain_usecase

import pl.wfranik.data_contract.GenresRepository
import pl.wfranik.domain_models.Genre
import javax.inject.Inject

class GetSelectedGenreUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {

    operator fun invoke(): Genre = genresRepository.getSelectedGenre()
}
