package pl.wfranik.moviesapp.domain

import pl.wfranik.domain_models.Genre
import pl.wfranik.moviesapp.data.genre.GenresRepository
import javax.inject.Inject

class GetSelectedGenreUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {

    operator fun invoke(): Genre = genresRepository.getSelectedGenre()
}
