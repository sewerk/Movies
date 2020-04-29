package pl.srw.movies.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.srw.movies.commons.api.Movie

class ListViewModel() : ViewModel() {

    val movieItems = MutableLiveData<List<Movie>>()

    init {
        movieItems.value = listOf(
            Movie("https://www.omdbapi.com/src/poster.jpg"),
            Movie(),
            Movie("https://www.omdbapi.com/src/poster.jpg"),
            Movie()
        )
    }
}
