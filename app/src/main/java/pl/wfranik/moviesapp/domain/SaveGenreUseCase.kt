package pl.wfranik.moviesapp.domain

import pl.wfranik.moviesapp.data.genre.GenresRepository
import pl.wfranik.moviesapp.domain.model.Genre
import javax.inject.Inject

class SaveGenreUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {

    suspend operator fun invoke(genre: Genre) =
        genresRepository.saveGenre(genre)
}
