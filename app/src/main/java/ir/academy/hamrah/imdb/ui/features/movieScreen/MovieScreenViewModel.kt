package ir.academy.hamrah.imdb.ui.features.movieScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.academy.hamrah.imdb.model.data.repository.MovieRepository
import ir.academy.hamrah.imdb.utils.EMPTY_MOVIE_INFO
import ir.academy.hamrah.imdb.utils.EMPTY_MOVIE_LIST
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieScreenViewModel(private val movieRepository: MovieRepository) : ViewModel(){
    val movieInfo = mutableStateOf(EMPTY_MOVIE_INFO)
    val moviesList = mutableStateOf(EMPTY_MOVIE_LIST)

    fun getMovieInfo(imdbId: String){
        viewModelScope.launch(Dispatchers.IO) {
            movieInfo.value = movieRepository.getMovieInfo(imdbId)
        }
    }

    fun getMoviesList(searchTerm: String) {
        viewModelScope.launch(Dispatchers.IO) {

            moviesList.value = movieRepository.getMoviesList(searchTerm)
        }
    }
}