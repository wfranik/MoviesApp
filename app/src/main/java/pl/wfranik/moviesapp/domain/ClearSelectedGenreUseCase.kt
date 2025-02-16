package pl.wfranik.moviesapp.domain

import pl.wfranik.data_contract.GenresRepository
import javax.inject.Inject

class ClearSelectedGenreUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {

    suspend operator fun invoke() =
        genresRepository.clearSelectedGenre()
}
