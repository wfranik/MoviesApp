package pl.wfranik.moviesapp.ui.home.model

import pl.wfranik.moviesapp.domain.model.MovieWithDetails
import pl.wfranik.moviesapp.extensions.formatCurrency
import pl.wfranik.moviesapp.ui.common.components.ImagePathBuilder
import javax.inject.Inject

class MovieListItemMapper @Inject constructor() {

    operator fun invoke(movieWithDetails: MovieWithDetails): MovieListItem {
        with(movieWithDetails) {
            return MovieListItem(
                id = movie.id,
                title = movie.title,
                imageUrl = ImagePathBuilder()
                    .withResource(movie.imageUrl)
                    .withSize(ImagePathBuilder.BackdropSize.W1280)
                    .build(),
                rating = movie.rating,
                revenue = movieDetails.revenue.formatCurrency(),
                budget = movieDetails.budget.formatCurrency()
            )
        }
    }
}
