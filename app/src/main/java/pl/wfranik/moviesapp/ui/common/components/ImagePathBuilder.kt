package pl.wfranik.moviesapp.ui.common.components

class ImagePathBuilder {

    private var resource: String? = null
    private var size: ImageSize? = null

    fun withResource(resource: String?): ImagePathBuilder {
        this.resource = resource
        return this
    }

    fun withSize(size: ImageSize): ImagePathBuilder {
        this.size = size
        return this
    }

    fun build(): String {
        return if (resource != null && size != null) {
            BASE_POSTER_PATH + size!!.p + resource
        } else {
            ""
        }
    }

    interface ImageSize {
        val p: String
    }

    enum class PosterSize(override val p: String) : ImageSize {
        W92("w92"),
        W154("w154"),
        W185("w185"),
        W342("w342"),
        W500("w500"),
        W780("w780"),
        ORIGINAL("original"),
    }

    enum class BackdropSize(override val p: String) : ImageSize {
        W300("w300"),
        W780("w780"),
        W1280("w1280"),
        ORIGINAL("original"),
    }

    companion object {
        private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/"
    }
}
