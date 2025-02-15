package pl.wfranik.moviesapp.api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import pl.wfranik.moviesapp.api.TmdbService
import pl.wfranik.moviesapp.api.TmdbServiceImpl

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providesTmdbService(
        httpClient: HttpClient
    ): TmdbService =
        TmdbServiceImpl(
            httpClient = httpClient
        )
}
