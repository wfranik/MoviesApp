package pl.wfranik.moviesapp.domain

import kotlinx.coroutines.flow.Flow
import pl.wfranik.moviesapp.data.genre.GenresRepository
import pl.wfranik.moviesapp.domain.model.Genre
import javax.inject.Inject

class ObserveSelectedGenreUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {

    operator fun invoke(): Flow<Genre> =
        genresRepository.observeSelectedGenre()
}
