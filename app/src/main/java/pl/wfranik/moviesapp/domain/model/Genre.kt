package pl.wfranik.moviesapp.domain.model

import pl.wfranik.moviesapp.R
import pl.wfranik.moviesapp.ui.common.utils.TextLabel

data class Genre(
    val id: Int,
    val name: TextLabel,
) {

    companion object {
        val DEFAULT = Genre(id = -1, TextLabel(R.string.genre_default_option_label))
    }
}
