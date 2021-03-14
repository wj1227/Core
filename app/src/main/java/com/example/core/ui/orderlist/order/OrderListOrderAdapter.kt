package com.example.core.ui.orderlist.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.order.Order
import com.example.core.databinding.ItemOrderBinding

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
        this.itemList.clear()
        this.itemList.addAll(item)
        this.notifyDataSetChanged()
    }
}