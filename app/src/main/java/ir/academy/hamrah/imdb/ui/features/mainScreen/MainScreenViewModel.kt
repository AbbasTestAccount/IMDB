package ir.academy.hamrah.imdb.ui.features.mainScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.academy.hamrah.imdb.model.data.MoviesList
import ir.academy.hamrah.imdb.model.data.repository.MovieRepository
import ir.academy.hamrah.imdb.utils.EMPTY_MOVIE_LIST
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val moviesList = mutableStateOf<MoviesList>(EMPTY_MOVIE_LIST)

    fun getMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {

            moviesList.value = movieRepository.getMoviesList()
        }
    }

}