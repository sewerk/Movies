package pl.srw.movies.di

import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.srw.movies.ui.main.MainViewModel

val mainModule = module {
    viewModel { (handle: SavedStateHandle) -> MainViewModel(handle) }
}