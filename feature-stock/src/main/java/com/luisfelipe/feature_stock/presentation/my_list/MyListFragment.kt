package com.luisfelipe.feature_stock.presentation.my_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.luisfelipe.extensions.verticalRecyclerViewLayout
import com.luisfelipe.feature_stock.di.StockModule.ADAPTER
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.stock.R
import com.luisfelipe.stock.databinding.FragmentMyListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MyListFragment : Fragment(R.layout.fragment_my_list) {

    private var _binding: FragmentMyListBinding? = null
    private val binding get() = _binding!!

    @Named(ADAPTER)
    @Inject
    lateinit var stocksAdapter: StockAdapter

    private val viewModel: MyListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getStockList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyListBinding.inflate(layoutInflater, container, false)
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
            ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(this)
        }
    }

    private val swipeToDeleteCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val (position, stock) = stocksAdapter.remove(viewHolder.adapterPosition)
                showDeletedItemSnackbar(position, stock)
            }
        }

    private fun showDeletedItemSnackbar(position: Int, stock: Stock) {
        Snackbar.make(
            binding.recyclerViewStocks,
            stock.companyName + " " + context?.getString(R.string.warning_item_deleted),
            Snackbar.LENGTH_LONG
        )
            .setAction(context?.getString(R.string.undo)) { stocksAdapter.add(position, stock) }
            .show()
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