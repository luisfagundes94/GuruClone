package com.luisfelipe.feature_stock.presentation.my_list

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.utils.load
import com.luisfelipe.feature_stock.utils.setOnDebouncedLongClickListener
import com.luisfelipe.stock.R
import com.luisfelipe.stock.databinding.StockItemBinding
import java.util.*

class MyListAdapter(context: Context) : RecyclerView.Adapter<MyListAdapter.StockViewHolder>() {

    private val stocks = mutableListOf<Stock>()
    private val positiveColorHex = context.resources.getString(0+R.color.green_300)
    private val negativeColorHex = context.resources.getString(0+R.color.red)

    private var onStockLongClickListener: ((stock: Stock) -> Unit)? = null

    fun setOnDebouncedLongClickListener(listener: (stock: Stock) -> Unit) {
        this.onStockLongClickListener = listener
    }

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

    fun getSelectedItems() = stocks.filter { it.isSelected }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val itemBinding = StockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(stocks[position])
    }

    override fun getItemCount() = stocks.size

    inner class StockViewHolder(private val itemBinding: StockItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        private val variationPercent = itemBinding.variationPercent

        fun bind(stock: Stock) {
            itemBinding.cardView.setOnDebouncedLongClickListener { onStockLongClickListener?.invoke(stock) }
            itemBinding.imgCompany.load(stock.company.logo)
            itemBinding.ticker.text = stock.ticker
            itemBinding.companyName.text = stock.company.name
            itemBinding.price.text = stock.price.toString()
            updateVariationPercentText(stock)
        }

        private fun updateVariationPercentText(stock: Stock) {
            if (stock.variationPercent < 0) {
                variationPercent.setTextColor(Color.parseColor(negativeColorHex))
                variationPercent.text = stock.getFormattedVariationInPercentage()
            } else {
                variationPercent.setTextColor(Color.parseColor(positiveColorHex))
                variationPercent.text = stock.getFormattedVariationInPercentage()
            }
        }

        private fun updateItemBackgroundColor(isItemSelected: Boolean) {
            if (isItemSelected) itemBinding.cardView.setBackgroundResource(R.color.light_green)
            else itemBinding.cardView.setCardBackgroundColor(Color.TRANSPARENT)
        }
    }
}
