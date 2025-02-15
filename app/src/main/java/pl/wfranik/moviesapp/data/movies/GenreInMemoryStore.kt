package pl.wfranik.moviesapp.data.movies

import kotlinx.coroutines.flow.MutableStateFlow
import pl.wfranik.moviesapp.domain.model.Genre

class GenreInMemoryStore {

    private val selectedGenre = MutableStateFlow(Genre.DEFAULT)

    fun getSelectedGenre() = selectedGenre.value

    fun setSelectedGenre(genre: Genre) {
        selectedGenre.value = genre
    }

    fun clearSelectedGenre() {
        selectedGenre.value = Genre.DEFAULT
    }

    fun observeSelectedGenre() = selectedGenre
}
