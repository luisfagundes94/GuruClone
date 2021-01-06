package com.luisfelipe.feature_stock.presentation.my_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.luisfelipe.extensions.verticalRecyclerViewLayout
import com.luisfelipe.stock.R
import com.luisfelipe.stock.databinding.FragmentMyListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyListFragment : Fragment(R.layout.fragment_my_list) {

    private var _binding: FragmentMyListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var stocksAdapter: StockAdapter

    private val viewModel: MyListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMyListBinding.bind(view)

        initRecyclerView()
        initViewModelObservers()
    }

    private fun initRecyclerView() {
        binding.recyclerViewStocks.apply {
            setHasFixedSize(true)
            layoutManager = verticalRecyclerViewLayout()
            adapter = stocksAdapter
        }
    }

    private fun initViewModelObservers() {
        viewModel.apply {

        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}