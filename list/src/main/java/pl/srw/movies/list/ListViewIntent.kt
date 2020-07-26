package pl.srw.movies.list

internal sealed class ListViewIntent {

    data class Fetch(val query: String) : ListViewIntent()
}