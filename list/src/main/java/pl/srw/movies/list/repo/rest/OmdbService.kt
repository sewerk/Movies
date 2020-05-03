package pl.srw.movies.list.repo.rest

import retrofit2.http.GET
import retrofit2.http.Query

internal interface OmdbService {

    @GET("/")
    suspend fun search(@Query("s") query: String, @Query("page") page: Int): SearchResponse
}