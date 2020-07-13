package pl.srw.movies.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.list_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.srw.movies.commons.utils.UiState
import pl.srw.movies.commons.utils.exhaustive
import pl.srw.movies.commons.utils.observe
import pl.srw.movies.commons.utils.toast
import pl.srw.movies.list.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModel()
    private lateinit var binding: ListFragmentBinding
    private val listAdapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@ListFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar(view)
        listView.adapter = listAdapter
        handleContentChanges()
    }

    private fun setupToolBar(view: View) {
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(view.toolbar)
    }

    private fun handleContentChanges() {
        observe(viewModel.state) {
            var inProgress = View.GONE
            when (it) {
                is UiState.Success -> listAdapter.submitList(it.data)
                is UiState.Error -> toast("Error: ${it.errorMessage}")
                UiState.InProgress -> inProgress = View.VISIBLE
            }.exhaustive
            binding.progressVisibility = inProgress
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.list_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnSearchClickListener {
            searchView.setQuery(viewModel.searchQuery, false)
            toolbar.setTitle(R.string.app_name)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newQuery: String?): Boolean {
                newQuery?.let {
                    viewModel.fetchMovies(newQuery)
                }
                searchView.onActionViewCollapsed()
                toolbar.title = "${toolbar.title}: ${viewModel.searchQuery}"
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean = false
        })
    }
}
