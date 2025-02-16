package pl.wfranik.domain_usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import pl.wfranik.data_contract.MoviesRepository
import pl.wfranik.domain_models.Genre
import pl.wfranik.domain_models.Movie
import pl.wfranik.domain_models.MovieDetails
import pl.wfranik.domain_models.MovieWithDetails
import pl.wfranik.domain_usecase.utils.toSuccess

class LoadMoviesWithDetailsUseCaseTest {

    private lateinit var repository: MoviesRepository

    private val selectedGenreDefault = Genre.DEFAULT
    private val selectedGenreAction = Genre(1, "Action")
    private val movieList = Movie.previewMovieList
    private val movie = Movie.previewMovie
    private val movieDetails = MovieDetails.previewMovieDetails
    private val moviesWithDetails = movieList.map { MovieWithDetails(it, movieDetails) }

    @Before
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun `should returns movies when selected genre is default`() = runTest {
        // given
        coEvery { repository.getMovies() } returns movieList.toSuccess()
        coEvery { repository.getMovieDetails(any()) } returns movieDetails.toSuccess()

        // when
        val result = LoadMoviesWithDetailsUseCase(repository, StandardTestDispatcher(testScheduler))
            .invoke(selectedGenreDefault)

        // then
        assert(result == Result.success(moviesWithDetails))
        coVerify { repository.getMovieDetails(movie) }
    }

    @Test
    fun `should returns filtered movies when selected genre is not default`() = runTest {
        // given
        coEvery { repository.getFilteredMovies(any()) } returns movieList.toSuccess()
        coEvery { repository.getMovieDetails(any()) } returns movieDetails.toSuccess()

        // when
        val result = LoadMoviesWithDetailsUseCase(repository, StandardTestDispatcher(testScheduler))
            .invoke(selectedGenreAction)

        // then
        assert(result == Result.success(moviesWithDetails))
        coVerify { repository.getMovieDetails(movie) }
    }

    // TODO: Fix this tests
//    @Test
//    fun `should returns result error with exception fetch movie details failed`() = runTest {
//        // given
//        val exception = Exception("Error")
//        coEvery { repository.getFilteredMovies(any()) } returns movieList.toSuccess()
//        coEvery { repository.getMovieDetails(any()) } returns Result.failure(exception)
//
//        // when
//        val result = LoadMoviesWithDetailsUseCase(repository, StandardTestDispatcher(testScheduler))
//            .invoke(selectedGenreAction)
//
//        // then
//        assert(result == Result.failure<Exception>(exception))
//    }
}
