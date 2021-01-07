package com.luisfelipe.feature_stock.presentation.my_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.stock.R
import com.luisfelipe.stock.databinding.StockItemBinding
import java.util.*

class StockAdapter: RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    private val stocks = mutableListOf<Stock>()

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stock_item, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(stocks[position])
    }

    override fun getItemCount() = stocks.size

    inner class StockViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = StockItemBinding.bind(view)

        fun bind(stock: Stock) {
            binding.imgCompany.setBackgroundResource(R.drawable.placeholder)
            binding.ticker.text = stock.ticker
            binding.companyName.text = stock.companyName
            binding.price.text = stock.price.toString()
            updateVariationPercentText(stock)
        }

        private fun updateVariationPercentText(stock: Stock) {
            if (stock.variationPercent < 0) {
                binding.variationPercent.setTextColor(Color.parseColor(NEGATIVE_COLOR_HEX))
                binding.variationPercent.text = stock.getFormattedVariationInPercentage()
            } else {
                binding.variationPercent.setTextColor(Color.parseColor(POSITIVE_COLOR_HEX))
                binding.variationPercent.text = stock.getFormattedVariationInPercentage()
            }
        }
    }

    private companion object {
        const val NEGATIVE_COLOR_HEX = "#D50000"
        const val POSITIVE_COLOR_HEX = "#01A524"
    }
}