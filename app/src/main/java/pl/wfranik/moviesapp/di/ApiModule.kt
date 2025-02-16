package pl.wfranik.moviesapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import pl.wfranik.api.TmdbServiceImpl
import pl.wfranik.datasource_api_contract.TmdbService

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
