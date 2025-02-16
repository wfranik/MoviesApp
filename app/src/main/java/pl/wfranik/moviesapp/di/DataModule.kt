package pl.wfranik.moviesapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.wfranik.api.TmdbService
import pl.wfranik.data_contract.GenresRepository
import pl.wfranik.data_contract.MoviesRepository
import pl.wfranik.data_implementation.mapper.MovieDTOMapper
import pl.wfranik.data_implementation.mapper.MovieDetailsDTOMapper
import pl.wfranik.data_implementation.movies.GenreInMemoryStore
import pl.wfranik.data_implementation.movies.MoviesRepositoryImpl
import pl.wfranik.data_implementation.genre.GenresRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesMoviesRepository(
        tmdbService: TmdbService,
        movieDTOMapper: MovieDTOMapper,
        movieDetailsDTOMapper: MovieDetailsDTOMapper
    ): MoviesRepository = MoviesRepositoryImpl(
        tmdbService = tmdbService,
        movieDTOMapper = movieDTOMapper,
        movieDetailsDTOMapper = movieDetailsDTOMapper
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
