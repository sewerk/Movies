package pl.srw.movies.list

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.srw.movies.commons.api.Movie
import pl.srw.movies.commons.test.InstantTaskExecutorExtension
import pl.srw.movies.commons.test.test
import pl.srw.movies.commons.utils.UiState
import pl.srw.movies.list.repo.MovieRepository
import java.io.IOException

@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class)
internal class ListViewModelTest {

    val testDispatcher = TestCoroutineDispatcher()

    val repository: MovieRepository = mock()
    val tested by lazy { ListViewModel(repository) }

    @BeforeEach
    fun beforeEach() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when created then ui state is in progress`() = testDispatcher.runBlockingTest {
        val observer = tested.state.test()

        observer.assertValueAt(0) {
            it shouldBeInstanceOf UiState.InProgress::class
        }
    }

    @Test
    fun `when created then Android movie list is fetched`() = testDispatcher.runBlockingTest {
        tested.state.test()

        verify(repository).fetchMovies("Android", 1)
    }

    @Test
    fun `when fetch movie called then movie list for query is fetched`() = testDispatcher.runBlockingTest {
        tested.state.test()

        tested.fetchMovies("Any")

        verify(repository).fetchMovies("Any", 1)
    }

    @Test
    fun `when movie list is fetched then data are exposed`() = testDispatcher.runBlockingTest {
        val responseList = listOf(Movie(""))
        whenever(repository.fetchMovies(any(), any())) doReturn responseList

        val observer = tested.state.test()

        observer.assertValueAt(1) {
            (it as UiState.Success).data shouldBeEqualTo responseList
        }
    }

    @Test
    fun `when movie list fetch failed then state is error`() = testDispatcher.runBlockingTest {
        whenever(repository.fetchMovies(any(), any())) doThrow IOException()

        val observer = tested.state.test()

        observer.assertValueAt(1) {
            it shouldBeInstanceOf UiState.Error::class
        }
    }
}
