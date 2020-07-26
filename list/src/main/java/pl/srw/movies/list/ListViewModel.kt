package pl.srw.movies.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pl.srw.movies.commons.api.Movie
import pl.srw.movies.commons.utils.UiState
import pl.srw.movies.list.repo.MovieRepository
import pl.srw.movies.list.uc.SearchUseCase
import timber.log.Timber

@ExperimentalCoroutinesApi
internal class ListViewModel(
    private val repo: MovieRepository,
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _intentChannel = ConflatedBroadcastChannel<ListViewIntent>()

    init {
        _intentChannel
            .asFlow()
            .onEach { viewIntent ->
                when (viewIntent) {
                    is ListViewIntent.Fetch -> searchUseCase.execute(viewIntent.query)
                }
            }
            .launchIn(viewModelScope)
    }

    suspend fun processIntent(intent: ListViewIntent) {
        _intentChannel.send(intent)
    }

    val state = searchUseCase
        .observe()
        .asLiveData(viewModelScope.coroutineContext)
    var searchQuery: String = "Android"

    private val _state = MutableLiveData<UiState<List<Movie>>>()

//    val state = liveData {
//        emit(UiState.InProgress)
//        emit(fetchMoviesResult(searchQuery))
//        emitSource(_state)
//    }

    fun fetchMovies(query: String) {
        if (searchQuery == query) return
        viewModelScope.launch {
            searchQuery = query
            _state.value = fetchMoviesResult(query)
        }
    }

    private suspend fun fetchMoviesResult(query: String): UiState<List<Movie>> {
        return try {
            val data = repo.fetchMovies(query)
            UiState.Success(data)
        } catch (ex: Exception) {
            Timber.e(ex, "Search movie failed")
            UiState.Error(ex.message)
        }
    }
}
