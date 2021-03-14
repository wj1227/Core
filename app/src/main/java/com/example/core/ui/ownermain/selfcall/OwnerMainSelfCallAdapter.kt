package com.example.core.ui.ownermain.selfcall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.databinding.ItemOwnermainSelfcallBinding

class OwnerMainSelfCallAdapter : RecyclerView.Adapter<OwnerMainSelfCallAdapter.ItemHolder>() {
    private val selfcallList = mutableListOf<SelfCallItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(selfcallList[position])
    }

    override fun getItemCount() = selfcallList.size

    class ItemHolder(
        private val binding: ItemOwnermainSelfcallBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SelfCallItem) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object Factory {
            fun create(parent: ViewGroup): ItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemOwnermainSelfcallBinding.inflate(layoutInflater, parent, false)

                return ItemHolder(view)
            }
        }
    }

    fun addItems(items: List<SelfCallItem>) {
        this.selfcallList.clear()
        this.selfcallList.addAll(items)
        this.notifyDataSetChanged()
    }
}