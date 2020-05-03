package pl.srw.movies.list.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.srw.movies.list.ListViewModel
import pl.srw.movies.list.repo.MovieRepository
import pl.srw.movies.list.repo.rest.OmdbService
import retrofit2.Retrofit
import retrofit2.create

val listModule = module {
    viewModel { ListViewModel(get()) }
    single { MovieRepository(get()) }
    factory { get<Retrofit>().create<OmdbService>() }
}