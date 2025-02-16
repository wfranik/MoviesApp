package pl.wfranik.domain_models

data class Genre(
    val id: Int,
    val name: String,
) {

    companion object {
        val DEFAULT = Genre(id = -1, "")
    }
}
