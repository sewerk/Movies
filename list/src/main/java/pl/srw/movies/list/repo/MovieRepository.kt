package pl.srw.movies.list.repo

import pl.srw.movies.commons.api.Movie
import pl.srw.movies.list.repo.rest.OmdbService
import java.io.IOException

internal class MovieRepository(
    private val service: OmdbService
) {

    @Throws(IOException::class)
    suspend fun fetchMovies(searchTitle: String = "", page: Int = 1): List<Movie> {
        return service.search(searchTitle, page)
            .result.map { it.toMovie() }
    }
}