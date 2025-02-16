package pl.wfranik.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageDTO<T>(
    @SerialName("page")
    val pageNo: Int,
    @SerialName("results")
    val resultList: List<T>,
)
