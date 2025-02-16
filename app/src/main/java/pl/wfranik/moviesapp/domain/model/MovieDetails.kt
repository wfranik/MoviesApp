package pl.wfranik.moviesapp.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val revenue: Long,
    val budget: Long
) {

    companion object {
        val previewMovieDetails = MovieDetails(
            id = 1,
            title = "Movie Title",
            originalTitle = "Movie Original Title",
            overview = "Movie Overview",
            revenue = 1000000,
            budget = 1000000
        )

        val previewMovieDetailsList = listOf(
            MovieDetails(
                id = 1,
                title = "Movie Title",
                originalTitle = "Movie Original Title",
                overview = "Movie Overview",
                revenue = 1000000,
                budget = 1000000
            ),
            MovieDetails(
                id = 2,
                title = "Movie Title",
                originalTitle = "Movie Original Title",
                overview = "Movie Overview",
                revenue = 1000000,
                budget = 1000000
            ),
            MovieDetails(
                id = 3,
                title = "Movie Title",
                originalTitle = "Movie Original Title",
                overview = "Movie Overview",
                revenue = 1000000,
                budget = 1000000
            ),
            MovieDetails(
                id = 4,
                title = "Movie Title",
                originalTitle = "Movie Original Title",
                overview = "Movie Overview",
                revenue = 1000000,
                budget = 1000000
            ),
            MovieDetails(
                id = 5,
                title = "Movie Title",
                originalTitle = "Movie Original Title",
                overview = "Movie Overview",
                revenue = 1000000,
                budget = 1000000
            ),
            MovieDetails(
                id = 6,
                title = "Movie Title",
                originalTitle = "Movie Original Title",
                overview = "Movie Overview",
                revenue = 1000000,
                budget = 1000000
            ),
        )
    }
}

