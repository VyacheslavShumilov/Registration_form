package com.hfad.room.users.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.room.databinding.ItemUsersBinding
import com.hfad.room.model.Users

class AdapterUsers(
    val users: ArrayList<Users>,
    val listener: SetOnClickListener
    ): RecyclerView.Adapter<AdapterUsers.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterUsers.ViewHolder {
        return ViewHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterUsers.ViewHolder, position: Int) {
        holder.bindView(users[position])
    }

    override fun getItemCount() = users.size

    inner class ViewHolder(var binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(users: Users) {
            binding.idTextView.text = users.id.toString()
            binding.nameTextView.text = users.name
            binding.emailTextView.text = users.email
            binding.phoneTextView.text = users.phone

            binding.deleteUser.setOnClickListener {
                listener.onClickDeleteUser(users)
                deleteUser(users)
            }
            itemView.setOnClickListener {
                listener.onClickUser(users.id)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteUser(users: Users){
        this.users.remove(users)
        notifyDataSetChanged()
    }

    interface SetOnClickListener {
        fun onClickDeleteUser(users: Users)
        fun onClickUser(id:Int)
    }

}