package pl.wfranik.moviesapp.data.mapper

import com.iteo.api.model.MovieDetailsDTO
import pl.wfranik.domain_models.MovieDetails
import javax.inject.Inject

class MovieDetailsDTOMapper @Inject constructor() {

    operator fun invoke(movieDetailsDto: MovieDetailsDTO) = MovieDetails(
        id = movieDetailsDto.id,
        title = movieDetailsDto.title,
        originalTitle = movieDetailsDto.originalTitle,
        overview = movieDetailsDto.overview,
        revenue = movieDetailsDto.revenue,
        budget = movieDetailsDto.budget
    )
}
