package pl.wfranik.moviesapp.ui.home.model

import pl.wfranik.moviesapp.ui.common.components.ImagePathBuilder
import pl.wfranik.moviesapp.ui.common.components.ImagePathBuilder.BackdropSize.W1280
import pl.wfranik.domain_models.MovieWithDetails
import pl.wfranik.moviesapp.extensions.formatCurrency
import javax.inject.Inject

class MovieListItemMapper @Inject constructor() {

    operator fun invoke(movieWithDetails: MovieWithDetails): MovieListItem {
        with(movieWithDetails) {
            return MovieListItem(
                id = movie.id,
                title = movie.title,
                imageUrl = ImagePathBuilder()
                    .withResource(movie.imageUrl)
                    .withSize(W1280)
                    .build(),
                rating = movie.rating,
                revenue = movieDetails.revenue.formatCurrency(),
                budget = movieDetails.budget.formatCurrency()
            )
        }
    }
}
