package pl.srw.movies.commons.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T: Any> Fragment.observe(liveData: LiveData<T>, callback: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(callback))
}