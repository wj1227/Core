package com.example.core.ui.orderlist.suggestion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.suggestion.SuggestionItem
import com.example.core.databinding.ItemSuggestionBinding

class OrderListSuggestionAdapter : RecyclerView.Adapter<OrderListSuggestionAdapter.ItemHolder>() {
    private val itemList = mutableListOf<SuggestionItem>()

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
        private val binding: ItemSuggestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SuggestionItem) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object Factory {
            fun create(parent: ViewGroup): ItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemSuggestionBinding.inflate(layoutInflater, parent, false)

                return ItemHolder(view)
            }
        }
    }

    fun addItem(item: List<SuggestionItem>) {
        this.itemList.clear()
        this.itemList.addAll(item)
        this.notifyDataSetChanged()
    }

}