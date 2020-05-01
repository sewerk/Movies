package pl.srw.movies.list

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.srw.movies.commons.test.InstantTaskExecutorExtension

@ExtendWith(InstantTaskExecutorExtension::class)
internal class ListViewModelTest {

    val tested by lazy { ListViewModel() }

    @Test
    fun `when created then movie list is fetched`() {

        assertEquals(4, tested.movieItems.value!!.size)
    }

    @Disabled
    @Test
    fun `when created then failed`() {
        assertEquals(3, tested.movieItems.value!!.size)
    }
}
