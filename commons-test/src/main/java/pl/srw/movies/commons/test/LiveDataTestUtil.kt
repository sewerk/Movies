package pl.srw.movies.commons.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.test.assertEquals

/**
 * Gets the value of a [LiveData] or waits for it to have one, with a timeout.
 *
 * Use this extension from host-side (JVM) tests. It's recommended to use it alongside
 * `InstantTaskExecutorRule` or a similar mechanism to execute tasks synchronously.
 */
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    afterObserve.invoke()

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        this.removeObserver(observer)
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

class TestLiveDataObserver<T> : Observer<T> {
    private val values = mutableListOf<T>()

    override fun onChanged(t: T) {
        values += t
    }

    fun assertValueAt(idx: Int, assertion: (T) -> Unit) {
        assertion(values[idx])
    }

    fun assertCount(expected: Int) {
        assertEquals(expected, values.size, "Count of received values does not match")
    }
}

fun <T> LiveData<T>.test(): TestLiveDataObserver<T> {
    val observer = TestLiveDataObserver<T>()
    observeForever(observer)
    return observer
}