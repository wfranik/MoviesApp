package pl.wfranik.moviesapp.domain

import pl.wfranik.moviesapp.data.genre.GenresRepository
import pl.wfranik.moviesapp.domain.model.Genre
import javax.inject.Inject

class GetSelectedGenreUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {

    operator fun invoke(): Genre = genresRepository.getSelectedGenre()
}
