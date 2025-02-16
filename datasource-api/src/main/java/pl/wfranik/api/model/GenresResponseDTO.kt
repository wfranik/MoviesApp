package pl.wfranik.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponseDTO(
    @SerialName("genres")
    val genres: List<GenreDTO>
)
