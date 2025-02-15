package pl.wfranik.moviesapp.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.wfranik.moviesapp.api.TmdbService
import pl.wfranik.moviesapp.data.genre.GenresRepository
import pl.wfranik.moviesapp.data.genre.GenresRepositoryImpl
import pl.wfranik.moviesapp.data.movies.GenreInMemoryStore
import pl.wfranik.moviesapp.data.movies.MoviesRepository
import pl.wfranik.moviesapp.data.movies.MoviesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesMoviesRepository(
        tmdbService: TmdbService
    ): MoviesRepository = MoviesRepositoryImpl(
        tmdbService = tmdbService
    )

    @Provides
    @Singleton
    fun providesGenreInMemoryStore(): GenreInMemoryStore = GenreInMemoryStore()

    @Provides
    fun providesGenresRepository(
        tmdbService: TmdbService,
        genreInMemoryStore: GenreInMemoryStore
    ): GenresRepository = GenresRepositoryImpl(
        tmdbService = tmdbService,
        genreInMemoryStore = genreInMemoryStore
    )
}
