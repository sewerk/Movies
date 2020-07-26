package pl.srw.movies.list.uc

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import pl.srw.movies.commons.api.Movie
import pl.srw.movies.commons.utils.UiState
import pl.srw.movies.list.repo.MovieRepository
import timber.log.Timber

@ExperimentalCoroutinesApi
internal class SearchUseCase(
    private val repo: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val channel = ConflatedBroadcastChannel<String>()

    fun execute(query: String) {
        channel.sendBlocking(query)
    }

    fun observe(): Flow<UiState<List<Movie>>> = channel.asFlow()
        .distinctUntilChanged()
        .flatMapLatest { doWork(it) }
        .flowOn(dispatcher)

//    fun produce(query: String): Flow<UiState<List<Movie>>> = doWork(query)
//        .flowOn(dispatcher)

    private suspend fun doWork(query: String): Flow<UiState<List<Movie>>> {
        return try {
            val data = repo.fetchMovies(query)
            UiState.Success(data)
        } catch (ex: Exception) {
            Timber.e(ex, "Search movie failed")
            UiState.Error(ex.message)
        }
    }
}