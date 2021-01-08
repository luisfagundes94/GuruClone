package com.luisfelipe.feature_stock.presentation.stock_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.luisfelipe.feature_stock.di.StockModule.ADAPTER
import com.luisfelipe.feature_stock.utils.RecyclerViewGesturesCallback
import com.luisfelipe.feature_stock.utils.verticalRecyclerViewLayout
import com.luisfelipe.stock.R
import com.luisfelipe.stock.databinding.FragmentStockListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class StockListFragment : Fragment(R.layout.fragment_stock_list) {

    private var _binding: FragmentStockListBinding? = null
    private val binding get() = _binding!!

    @Named(ADAPTER)
    @Inject
    lateinit var stocksAdapter: StockAdapter

    private val viewModel: StockListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getStockList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initViewModelObservers()
    }

    private fun initRecyclerView() {
        binding.recyclerViewStocks.apply {
            setHasFixedSize(true)
            layoutManager = verticalRecyclerViewLayout()
            adapter = stocksAdapter

            val recyclerViewGesturesCallback = RecyclerViewGesturesCallback(
                context,
                binding.recyclerViewStocks,
                stocksAdapter
            )
            ItemTouchHelper(recyclerViewGesturesCallback).attachToRecyclerView(this)
        }
    }

    private fun initViewModelObservers() {
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner, {
            })
            stocks.observe(viewLifecycleOwner, { stocks ->
                stocksAdapter.updateStocks(stocks)
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
