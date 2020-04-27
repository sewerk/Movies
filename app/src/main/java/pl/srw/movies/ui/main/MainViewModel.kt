package pl.srw.movies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val savedState: SavedStateHandle
) : ViewModel() {

    var count: LiveData<Int> = savedState.getLiveData("count", 0)

    fun onClick() {
        savedState.set("count", count.value!! + 1)
    }
}
