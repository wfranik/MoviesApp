package pl.wfranik.moviesapp.api.di

import androidx.compose.ui.text.intl.Locale
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pl.wfranik.moviesapp.BuildConfig
import pl.wfranik.moviesapp.api.Configuration
import pl.wfranik.moviesapp.api.Configuration.API_KEY_PARAMETER
import pl.wfranik.moviesapp.api.Configuration.AUTH_BEARER
import pl.wfranik.moviesapp.api.Configuration.AUTH_KEY_NAME
import pl.wfranik.moviesapp.api.Configuration.BASE_PATH
import pl.wfranik.moviesapp.api.Configuration.BEARER_KEY_NAME
import pl.wfranik.moviesapp.api.Configuration.LANGUAGE_KEY_PARAMETER
import timber.log.Timber

private const val TIMEOUT_MILLIS_REQUEST = 30000L
private const val TIMEOUT_MILLIS_CONNECT = 10000L
private const val TIMEOUT_MILLIS_SOCKET = 30000L

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpClient(): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT_MILLIS_REQUEST
            connectTimeoutMillis = TIMEOUT_MILLIS_CONNECT
            socketTimeoutMillis = TIMEOUT_MILLIS_SOCKET
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.tag("HttpClient").d(message)
                }
            }
            level = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = Configuration.HOST
                path(BASE_PATH)
                parameters.apply {
                    append(API_KEY_PARAMETER, BuildConfig.API_KEY_VALUE)
                    append(LANGUAGE_KEY_PARAMETER, Locale.current.toLanguageTag())
                }
            }
            headers {
                append(AUTH_KEY_NAME, "$BEARER_KEY_NAME $AUTH_BEARER")
            }
        }
    }
}
