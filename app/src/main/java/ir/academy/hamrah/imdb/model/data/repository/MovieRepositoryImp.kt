package ir.academy.hamrah.imdb.model.data.repository

import ir.academy.hamrah.imdb.model.data.MoviesList
import ir.academy.hamrah.imdb.model.net.ApiService

class MovieRepositoryImp(private val apiService: ApiService) : MovieRepository{
    override suspend fun getMoviesList(): MoviesList {
        return apiService.getMoviesList("Batman")
    }

}