package ir.academy.hamrah.imdb.di


import ir.academy.hamrah.imdb.model.data.repository.MovieRepository
import ir.academy.hamrah.imdb.model.data.repository.MovieRepositoryImp
import ir.academy.hamrah.imdb.model.net.createApiService
import ir.academy.hamrah.imdb.ui.features.mainScreen.MainScreenViewModel
import ir.academy.hamrah.imdb.ui.features.movieScreen.MovieScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {
    single { createApiService() }

    single<MovieRepository> { MovieRepositoryImp(get()) }

    viewModel { MainScreenViewModel(get()) }
    viewModel { MovieScreenViewModel(get()) }
}