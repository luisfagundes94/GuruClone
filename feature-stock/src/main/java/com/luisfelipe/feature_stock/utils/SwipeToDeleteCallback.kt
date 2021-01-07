package com.luisfelipe.feature_stock.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.feature_stock.presentation.my_list.StockAdapter

class SwipeToDeleteCallback(
    private val adapter: StockAdapter,
) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.remove(viewHolder.adapterPosition)
    }

}