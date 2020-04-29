package pl.srw.movies.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.srw.movies.commons.utils.observe
import pl.srw.movies.list.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModel()

    private val listAdapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ListFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = this@ListFragment.viewModel
            lifecycleOwner = this@ListFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.adapter = listAdapter
        observe(viewModel.movieItems) { listAdapter.submitList(it) }
    }
}
