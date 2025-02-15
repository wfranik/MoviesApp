package pl.wfranik.moviesapp.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.wfranik.moviesapp.api.TmdbService

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesMoviesRepository(
        tmdbService: TmdbService
    ): MoviesRepository =
        MoviesRepositoryImpl(
            tmdbService = tmdbService
        )
}
