package pl.srw.movies.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.srw.movies.commons.api.Movie
import pl.srw.movies.list.databinding.ListItemBinding

internal class ListAdapter :
    androidx.recyclerview.widget.ListAdapter<Movie, ListAdapter.MovieItemVH>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return MovieItemVH(binding)
    }

    override fun onBindViewHolder(holder: MovieItemVH, position: Int) {
        val item: Movie = getItem(position)
        holder.binding.data = item
    }

    class MovieItemVH(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private class Diff : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.posterUrl == oldItem.posterUrl
    }
}