package pl.wfranik.ui_home.model

import pl.wfranik.domain_models.MovieWithDetails
import pl.wfranik.ui_common.components.ImagePathBuilder
import pl.wfranik.ui_common.components.ImagePathBuilder.BackdropSize.W1280
import pl.wfranik.ui_common.extensions.formatCurrency
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
