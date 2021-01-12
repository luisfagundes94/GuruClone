package com.luisfelipe.feature_stock.presentation.my_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.luisfelipe.feature_stock.R
import com.luisfelipe.feature_stock.databinding.FragmentMyListBinding
import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.utils.RecyclerViewGesturesCallback
import com.luisfelipe.feature_stock.utils.toast
import com.luisfelipe.feature_stock.utils.verticalRecyclerViewLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyListFragment : Fragment(R.layout.fragment_my_list) {

    companion object {
        fun getInstance() = MyListFragment()
    }

    private var _binding: FragmentMyListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var myListAdapter: MyListAdapter

    private val viewModel: MyListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.verifyIfItIsTheUsersFirstTimeOnCache()
        //viewModel.getStockList()
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
            adapter = myListAdapter

            val recyclerViewGesturesCallback = RecyclerViewGesturesCallback(
                context,
                binding.recyclerViewStocks,
                myListAdapter
            )
            ItemTouchHelper(recyclerViewGesturesCallback).attachToRecyclerView(this)
        }
    }

    private fun initViewModelObservers() {
        viewModel.apply {
            isUserFirstTime.observe(viewLifecycleOwner, {
                when (it) {
                    true -> toast("firstTime!")
                    false -> toast("notTheFirstTime!")
                }
            })
            isLoading.observe(viewLifecycleOwner, {
                when (it) {
                    true -> { showShimmerEffect() }
                    false -> hideShimmerEffect()
                }
            })
            stockListResultStatus.observe(viewLifecycleOwner, { resultStatus ->
                when (resultStatus) {
                    is ResultStatus.Success -> myListAdapter.updateStocks(resultStatus.data)
                    is ResultStatus.Error -> toast(getString(R.string.warning_failed_to_fetch_stocks))
                    is ResultStatus.Empty -> toast(getString(R.string.warning_empty_list))
                }
            })
        }
    }

    private fun showShimmerEffect() {
        binding.shimmerFrameLayout.startShimmerAnimation()
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.recyclerViewStocks.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmerFrameLayout.stopShimmerAnimation()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.recyclerViewStocks.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
