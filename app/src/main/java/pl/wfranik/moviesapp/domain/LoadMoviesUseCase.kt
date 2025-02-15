package pl.wfranik.moviesapp.domain

import pl.wfranik.moviesapp.domain.model.Movie
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor() {

    suspend operator fun invoke(): Result<List<Movie>> {
        return Result.success(
            listOf(
                Movie(
                    id = "id1",
                    title = "The Dark Knight",
                    imageUrl = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg"
                ),
                Movie(
                    id = "id2",
                    title = "Inception",
                    imageUrl = "https://image.tmdb.org/t/p/w500/8h58R2S4NTpaRjIlkiTXRBeNzGr.jpg"
                ),
                Movie(
                    id = "id3",
                    title = "Interstellar",
                    imageUrl = "https://image.tmdb.org/t/p/w500/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg"
                ),
                Movie(
                    id = "id4",
                    title = "The Matrix",
                    imageUrl = "https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg"
                ),
                Movie(
                    id = "id5",
                    title = "Avengers: Endgame",
                    imageUrl = "https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg"
                ),
                Movie(
                    id = "id6",
                    title = "Joker",
                    imageUrl = "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg"
                )
            )
        )
    }
}
