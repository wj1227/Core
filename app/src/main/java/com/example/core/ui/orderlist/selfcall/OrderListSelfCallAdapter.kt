package com.example.core.ui.orderlist.selfcall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.databinding.ItemSelfcallBinding

class OrderListSelfCallAdapter : RecyclerView.Adapter<OrderListSelfCallAdapter.ItemHolder>() {
    private val itemList = mutableListOf<SelfCallItem>()

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
        private val binding: ItemSelfcallBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SelfCallItem) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object Factory {
            fun create(parent: ViewGroup): ItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemSelfcallBinding.inflate(layoutInflater, parent, false)

                return ItemHolder(view)
            }
        }
    }

    fun addItem(item: List<SelfCallItem>) {
        this.itemList.clear()
        this.itemList.addAll(item)
        this.notifyDataSetChanged()
    }

}