package pl.srw.movies.list.repo.rest

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SearchResponse(
    @Json(name = "Search") val result: List<MovieResponse> = emptyList(),
    val totalResults: Int = 0,
    @Json(name = "Response") val response: String? = null
)