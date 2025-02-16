package pl.wfranik.domain_models

data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rating: Float
) {

    companion object {
        val previewMovie = Movie(
            id = 1,
            title = "Movie Title",
            imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
            rating = 3.5f
        )

        val previewMovieList = listOf(
            Movie(
                id = 1,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f
            ),
            Movie(
                id = 2,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f
            ),
            Movie(
                id = 3,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f
            ),
            Movie(
                id = 4,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f
            ),
            Movie(
                id = 5,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f
            ),
            Movie(
                id = 6,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f
            ),
        )
    }
}
