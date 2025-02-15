package pl.wfranik.moviesapp.domain

import pl.wfranik.moviesapp.data.MoviesRepository
import pl.wfranik.moviesapp.domain.model.Genre
import javax.inject.Inject

class LoadGenresUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(): Result<List<Genre>> =
        moviesRepository.getGenres()
}
