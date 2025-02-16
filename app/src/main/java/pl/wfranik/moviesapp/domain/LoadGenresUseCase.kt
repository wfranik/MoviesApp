package pl.wfranik.moviesapp.domain

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import pl.wfranik.moviesapp.data.genre.GenresRepository
import pl.wfranik.moviesapp.domain.model.Genre
import javax.inject.Inject

class LoadGenresUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {

    suspend operator fun invoke(): Result<List<Genre>> = withContext(IO) {
        genresRepository.getGenres().map { genres ->
            genres.toMutableList().apply { add(index = 0, element = Genre.DEFAULT) }
        }
    }
}
