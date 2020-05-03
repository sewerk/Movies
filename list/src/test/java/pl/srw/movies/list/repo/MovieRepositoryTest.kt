package pl.srw.movies.list.repo

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import pl.srw.movies.list.repo.rest.OmdbService

@ExperimentalCoroutinesApi
internal class MovieRepositoryTest {

    val service: OmdbService = mock()
    val tested = MovieRepository(service)

    @Test
    fun `when fetch then calls service`() = runBlockingTest {
        whenever(service.search(any(), any())).thenReturn(mock())
        val query = "any"

        tested.fetchMovies(query, 1)

        verify(service).search(query, 1)
    }
}