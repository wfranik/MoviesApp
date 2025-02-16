package pl.wfranik.moviesapp.data.mapper

import pl.wfranik.domain_models.Movie
import pl.wfranik.moviesapp.api.model.MovieDTO
import javax.inject.Inject

class MovieDTOMapper @Inject constructor() {

    operator fun invoke(movieDto: MovieDTO) = Movie(
        id = movieDto.id,
        title = movieDto.title,
        imageUrl = movieDto.posterResourceName ?: "",
        rating = movieDto.voteAverage.toFloat()
    )
}
