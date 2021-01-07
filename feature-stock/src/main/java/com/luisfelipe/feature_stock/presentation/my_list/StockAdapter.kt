package com.luisfelipe.feature_stock.presentation.my_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.stock.R
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

    inner class StockViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val companyImage: ImageView = itemView.findViewById(R.id.img_company)
        private val ticker: TextView = itemView.findViewById(R.id.ticker)
        private val companyName: TextView = itemView.findViewById(R.id.company_name)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val variationPercent: TextView = itemView.findViewById(R.id.variation_percent)

        fun bind(stock: Stock) {
            companyImage.setBackgroundResource(R.drawable.placeholder)
            ticker.text = stock.ticker
            companyName.text = stock.companyName
            price.text = stock.price.toString()
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