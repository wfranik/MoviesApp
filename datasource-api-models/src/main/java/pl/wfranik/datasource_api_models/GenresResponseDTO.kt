package pl.wfranik.datasource_api_models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponseDTO(
    @SerialName("genres")
    val genres: List<GenreDTO>
)
