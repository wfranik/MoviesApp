package pl.wfranik.moviesapp.domain

import pl.wfranik.data_contract.GenresRepository
import pl.wfranik.domain_models.Genre
import javax.inject.Inject

class SaveGenreUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {

    suspend operator fun invoke(genre: Genre) =
        genresRepository.saveGenre(genre)
}
