package ir.academy.hamrah.imdb.model.data.repository

import ir.academy.hamrah.imdb.model.data.MovieInfo
import ir.academy.hamrah.imdb.model.data.MoviesList

interface MovieRepository {

    suspend fun getMoviesList(searchTerm: String, page : Int = 1): MoviesList

    suspend fun getMovieInfo(imdbId: String): MovieInfo


}