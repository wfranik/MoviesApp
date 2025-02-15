package pl.wfranik.moviesapp.domain

import pl.wfranik.moviesapp.data.MoviesRepository
import pl.wfranik.moviesapp.domain.model.Movie
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(): Result<List<Movie>> =
        moviesRepository.getMovies()
}
