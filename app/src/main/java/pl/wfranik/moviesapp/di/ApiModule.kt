package pl.wfranik.moviesapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import pl.wfranik.api.TmdbService
import pl.wfranik.api.TmdbServiceImpl

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
