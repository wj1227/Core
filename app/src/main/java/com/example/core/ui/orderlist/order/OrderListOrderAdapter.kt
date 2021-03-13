package com.example.core.ui.orderlist.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.order.Order
import com.example.core.databinding.ItemOrderBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class OrderListOrderAdapter : RecyclerView.Adapter<OrderListOrderAdapter.ItemHolder>() {
    private val itemList = mutableListOf<Order>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    class ItemHolder(
        private val binding: ItemOrderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Order) {
            val localDate = Date(item.date!!)
            val dt = SimpleDateFormat("yy년 MM월 dd일")
            val tz = TimeZone.getTimeZone("Asia/Seoul")
            val localTime = dt.format(localDate)
            dt.timeZone = tz
            binding.item = item
            binding.executePendingBindings()
        }

        companion object Factory {
            fun create(parent: ViewGroup): ItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemOrderBinding.inflate(layoutInflater, parent, false)

                return ItemHolder(view)
            }
        }
    }

    fun addItem(item: List<Order>) {
        this.itemList.addAll(item)
        this.notifyDataSetChanged()
    }
}