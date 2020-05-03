package pl.srw.movies.commons.utils

sealed class UiState<out D: Any> {

    data class Success<out D: Any>(val data: D) : UiState<D>()

    data class Error(val errorMessage: String?) : UiState<Nothing>()

    object InProgress : UiState<Nothing>()
}

inline fun <reified T: Any> T.toState() = UiState.Success(this) as UiState<T>

val <T> T.exhaustive: T
    get() = this