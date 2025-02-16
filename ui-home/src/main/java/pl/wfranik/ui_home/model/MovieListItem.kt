package pl.wfranik.ui_home.model

data class MovieListItem(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rating: Float,
    val revenue: String,
    val budget: String
) {

    companion object {
        val previewMovieListItem = MovieListItem(
            id = 1,
            title = "Movie Title",
            imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
            rating = 3.5f,
            revenue = "$452.72M",
            budget = "$310.00M"
        )

        val previewMovieListItemList = listOf(
            MovieListItem(
                id = 1,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f,
                revenue = "$452.72M",
                budget = "$310.00M"
            ),
            MovieListItem(
                id = 2,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f,
                revenue = "$452.72M",
                budget = "$310.00M"
            ),
            MovieListItem(
                id = 3,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f,
                revenue = "$452.72M",
                budget = "$310.00M"
            ),
            MovieListItem(
                id = 4,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f,
                revenue = "$452.72M",
                budget = "$310.00M"
            ),
            MovieListItem(
                id = 5,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f,
                revenue = "$452.72M",
                budget = "$310.00M"
            ),
            MovieListItem(
                id = 6,
                title = "Movie Title",
                imageUrl = "https://image.tmdb.org/t/p/w1280/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
                rating = 3.5f,
                revenue = "$452.72M",
                budget = "$310.00M"
            ),
        )
    }
}
