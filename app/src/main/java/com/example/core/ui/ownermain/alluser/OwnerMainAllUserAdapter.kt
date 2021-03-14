package com.example.core.ui.ownermain.alluser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.signin.SigninUser
import com.example.core.databinding.ItemOwnermainAlluserBinding

class OwnerMainAllUserAdapter : RecyclerView.Adapter<OwnerMainAllUserAdapter.ItemHolder>() {
    private val userList = mutableListOf<SigninUser>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size

    class ItemHolder(
        private val binding: ItemOwnermainAlluserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SigninUser) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object Factory {
            fun create(parent: ViewGroup): ItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemOwnermainAlluserBinding.inflate(layoutInflater, parent, false)

                return ItemHolder(view)
            }
        }
    }

    fun addItem(users: List<SigninUser>) {
        this.userList.clear()
        this.userList.addAll(users)
        this.notifyDataSetChanged()
    }
}