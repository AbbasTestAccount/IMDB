package ir.academy.hamrah.imdb.ui.features.mainScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.academy.hamrah.imdb.model.data.repository.MovieRepository
import ir.academy.hamrah.imdb.utils.EMPTY_MOVIE_LIST
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val moviesList = mutableStateOf(EMPTY_MOVIE_LIST)
    var pageNumber = mutableStateOf(1)
//    val showMovies = mutableStateOf(false)



    fun getMoviesList(searchTerm: String) {
        viewModelScope.launch {
            moviesList.value = movieRepository.getMoviesList(searchTerm, pageNumber.value)
        }
    }


    fun clearMoviesList(){
        viewModelScope.launch(Dispatchers.IO) {
            moviesList.value = EMPTY_MOVIE_LIST
        }
    }

}