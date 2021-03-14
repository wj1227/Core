package com.example.core.ui.ownermain.suggestion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.suggestion.SuggestionItem
import com.example.core.databinding.ItemOwnermainOrderSuggestionBinding

class OwnerMainSuggestionAdapter : RecyclerView.Adapter<OwnerMainSuggestionAdapter.ItemViewHolder>() {
    private val suggestionList = mutableListOf<SuggestionItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(suggestionList[position])
    }

    override fun getItemCount() = suggestionList.size

    class ItemViewHolder(
        private val binding: ItemOwnermainOrderSuggestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SuggestionItem) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object Factory {
            fun create(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemOwnermainOrderSuggestionBinding.inflate(layoutInflater, parent, false)

                return ItemViewHolder(view)
            }
        }
    }

    fun addItems(items: List<SuggestionItem>) {
        this.suggestionList.clear()
        this.suggestionList.addAll(items)
        this.notifyDataSetChanged()
    }
}