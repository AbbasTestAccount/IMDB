package ir.academy.hamrah.imdb.model.data.repository

import ir.academy.hamrah.imdb.model.data.MoviesList

interface MovieRepository {

    suspend fun getMoviesList() : MoviesList



}