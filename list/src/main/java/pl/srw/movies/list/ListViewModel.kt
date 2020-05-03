package pl.srw.movies.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import pl.srw.movies.commons.api.Movie
import pl.srw.movies.commons.utils.UiState
import pl.srw.movies.list.repo.MovieRepository
import timber.log.Timber

internal class ListViewModel(
    private val repo: MovieRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val state = MutableLiveData<UiState<List<Movie>>>(UiState.InProgress)

    init {
        fetchMovies("Android")
    }

    private fun fetchMovies(query: String) {
        viewModelScope.launch {
            try {
                val data = repo.fetchMovies(query)
                state.value = UiState.Success(data)
            } catch (ex: Exception) {
                Timber.e(ex, "Search movie failed")
                state.value = UiState.Error(ex.message)
            }
        }
    }
}
