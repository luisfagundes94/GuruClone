package com.luisfelipe.feature_stock.utils

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.presentation.my_list.StockAdapter
import com.luisfelipe.stock.R

class RecyclerViewFeaturesCallback(
    private val context: Context,
    private val recyclerView: RecyclerView,
    private val adapter: StockAdapter
): ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
    ItemTouchHelper.LEFT
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.move(viewHolder.adapterPosition, target.adapterPosition)
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val (position, stock) = adapter.remove(viewHolder.adapterPosition)
        showDeletedItemSnackbar(position, stock)
    }

//    override fun onChildDraw(
//        canvas: Canvas,
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder,
//        dX: Float,
//        dY: Float,
//        actionState: Int,
//        isCurrentlyActive: Boolean
//    ) {
//
//        RecyclerViewSwipeDecorator.Builder(
//            context,
//            canvas,
//            recyclerView,
//            viewHolder,
//            dX,
//            dY,
//            actionState,
//            isCurrentlyActive
//        ).addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.red))
//            .addSwipeLeftLabel(context.resources.getString(R.string.delete))
//            .setSwipeLeftLabelColor(ContextCompat.getColor(
//                context,
//                android.R.color.white
//            ))
//            .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
//            .setActionIconTint(
//                ContextCompat.getColor(
//                    context,
//                    android.R.color.white
//                )
//            )
//            .create()
//            .decorate()
//
//        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//    }

    private fun showDeletedItemSnackbar(position: Int, stock: Stock) {
        Snackbar.make(
            recyclerView,
            stock.companyName + " " + context.getString(R.string.warning_item_deleted),
            Snackbar.LENGTH_LONG
        )
            .setAction(context.getString(R.string.undo)) { adapter.add(position, stock) }
            .show()
    }
}