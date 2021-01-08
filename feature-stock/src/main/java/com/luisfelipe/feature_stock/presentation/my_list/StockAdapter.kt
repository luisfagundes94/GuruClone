package com.luisfelipe.feature_stock.presentation.my_list

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.extensions.load
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.stock.R
import com.luisfelipe.stock.databinding.StockItemBinding
import java.util.*

class StockAdapter: RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    private val stocks = mutableListOf<Stock>()
    private val selectedStocks = mutableListOf<Stock>()
    private var isActionModeEnabled = false

    fun updateStocks(stocks: List<Stock>) {
        if (this.stocks.isNotEmpty()) this.stocks.clear()

        this.stocks.addAll(stocks)
        notifyDataSetChanged()
    }

    fun add(position: Int, stock: Stock) {
        stocks.add(position, stock)
        notifyDataSetChanged()
    }

    fun remove(position: Int): Pair<Int, Stock> {
        val stock = stocks[position]
        stocks.remove(stock)
        notifyDataSetChanged()
        return Pair(position, stock)
    }

    fun move(fromPosition: Int, toPosition: Int) {
        Collections.swap(this.stocks, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

//    private fun selectItem(viewHolder: StockViewHolder) {
//        val selectedItem = stocks[viewHolder.adapterPosition]
//        viewHolder.itemView.setBackgroundColor(Color.LTGRAY)
//    }

//    private fun initActionMode(viewHolder: StockViewHolder) {
//        val callback = object : ActionMode.Callback {
//            override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
//                val menuInflater = actionMode?.menuInflater
//                menuInflater?.inflate(R.menu.multiple_items_selected_menu, menu)
//                return true
//            }
//
//            override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
//                isActionModeEnabled = true
//                selectItem(viewHolder)
//                return true
//            }
//
//            override fun onActionItemClicked(actionMode: ActionMode?, menu: MenuItem?): Boolean {
//                when (menu?.itemId) {
//                    R.id.menu_delete -> {
//                        stocks.removeAll(selectedStocks)
//                    }
//                    R.id.menu_select_all -> {
//                        if (selectedStocks.size == stocks.size) selectedStocks.clear()
//                        else {
//                            selectedStocks.clear()
//                            selectedStocks.addAll(stocks)
//                        }
//                    }
//                }
//                return false
//            }
//
//            override fun onDestroyActionMode(actionMode: ActionMode?) {
//                isActionModeEnabled = false
//                selectedStocks.clear()
//                notifyDataSetChanged()
//            }
//
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stock_item, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(stocks[position])

        holder.itemView.setOnClickListener {
            Log.d("onClick", "hello!")
        }
    }

    override fun getItemCount() = stocks.size

    inner class StockViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = StockItemBinding.bind(itemView)

        private val variationPercent = binding.variationPercent

        fun bind(stock: Stock) {
            binding.imgCompany.load(stock.company.logo)
            binding.ticker.text = stock.ticker
            binding.companyName.text = stock.company.name
            binding.price.text = stock.price.toString()
            updateVariationPercentText(stock)
        }

        private fun updateVariationPercentText(stock: Stock) {
            if (stock.variationPercent < 0) {
                variationPercent.setTextColor(Color.parseColor(NEGATIVE_COLOR_HEX))
                variationPercent.text = stock.getFormattedVariationInPercentage()
            } else {
                variationPercent.setTextColor(Color.parseColor(POSITIVE_COLOR_HEX))
                variationPercent.text = stock.getFormattedVariationInPercentage()
            }
        }
    }

    private companion object {
        const val NEGATIVE_COLOR_HEX = "#D50000"
        const val POSITIVE_COLOR_HEX = "#01A524"
    }
}