package pl.srw.movies.commons.api

data class Movie(
    val title: String,
    val posterUrl: String? = null,
    val rating: String? = null,
    val votes: String? = null
)
