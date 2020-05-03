package pl.srw.movies.list.repo.rest

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.srw.movies.commons.api.Movie

@JsonClass(generateAdapter = true)
internal data class MovieResponse(
    val imdbID: String,
    @Json(name = "Title") val title: String? = null,
    @Json(name = "Poster") val Poster: String? = null,
    val imdbRating: String? = null,
    val imdbVotes: String? = null
) {
    fun toMovie() = Movie(
        title = title.orEmpty(),
        posterUrl = Poster,
        rating = imdbRating,
        votes = imdbVotes
    )
}