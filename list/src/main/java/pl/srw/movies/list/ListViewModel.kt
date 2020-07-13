package pl.srw.movies.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.srw.movies.commons.api.Movie
import pl.srw.movies.commons.utils.UiState
import pl.srw.movies.list.repo.MovieRepository
import timber.log.Timber

internal class ListViewModel(
    private val repo: MovieRepository
) : ViewModel() {

    var searchQuery: String = "Android"

    private val _state = MutableLiveData<UiState<List<Movie>>>()
    val state = liveData {
        emit(UiState.InProgress)
        emit(fetchMoviesResult(searchQuery))
        emitSource(_state)
    }

    fun fetchMovies(query: String) {
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
