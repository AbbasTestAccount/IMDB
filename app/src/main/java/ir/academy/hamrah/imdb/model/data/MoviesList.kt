package ir.academy.hamrah.imdb.model.data

data class MoviesList(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
){
    fun isMovieListEmpty(): Boolean{
        return Response.isEmpty()
    }
}