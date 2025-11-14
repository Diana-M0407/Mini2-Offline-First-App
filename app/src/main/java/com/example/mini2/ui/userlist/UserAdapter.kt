package com.example.mini2.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mini2.data.local.UserEntity
import com.example.mini2.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val items = mutableListOf<UserEntity>()

    fun submitList(newItems: List<UserEntity>) {
        items.clear()
        items.addAll(newItems.sortedBy { it.id })
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = items[position]

        holder.binding.userId.text = "ID: ${user.id}"
        holder.binding.userName.text = "Name: ${user.name}"
        holder.binding.userUsername.text = "Username: ${user.username}"
        holder.binding.userEmail.text = "Email: ${user.email}"
        holder.binding.userPhone.text = "Phone: ${user.phone}"
        holder.binding.userWebsite.text = "Website: ${user.website}"
    }
}
