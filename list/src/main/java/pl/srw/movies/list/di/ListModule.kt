package pl.srw.movies.list.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.srw.movies.list.ListViewModel

val listModule = module {
    viewModel { ListViewModel() }
}