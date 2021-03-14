package com.example.core.ui.ownermain.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.order.Order
import com.example.core.databinding.ItemOwnermainOrderBinding

class OwnerMainOrderAdapter : RecyclerView.Adapter<OwnerMainOrderAdapter.ItemHolder>() {
    private val orderList = mutableListOf<Order>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(orderList[position])
    }

    override fun getItemCount() = orderList.size

    class ItemHolder(
        private val binding: ItemOwnermainOrderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Order) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object Factory {
            fun create(parent: ViewGroup): ItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemOwnermainOrderBinding.inflate(layoutInflater, parent, false)

                return ItemHolder(view)
            }
        }
    }

    fun addItems(items: List<Order>) {
        this.orderList.clear()
        this.orderList.addAll(items)
        this.notifyDataSetChanged()
    }
}