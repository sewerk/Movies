package pl.srw.movies.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.list_fragment.*
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
        listView.adapter = listAdapter
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
}
