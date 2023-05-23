package com.example.realmdatabase.mainscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.realmdatabase.R
import com.example.realmdatabase.data.model.Contact
import com.example.realmdatabase.databinding.ItemContactBinding
import com.example.realmdatabase.domain.entity.ContactModel

class ContactsAdapter(val onEditButtonClicked: (ContactModel) -> Unit) :
    ListAdapter<ContactModel, ContactsAdapter.MyViewHolder>(MyDiffUtil) {

    object MyDiffUtil : DiffUtil.ItemCallback<ContactModel>() {

        override fun areItemsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imageViewEdit: ImageView = binding.imageView
        fun bind(contact: ContactModel?) {
            binding.tvNameAndSurname.text = "${contact?.name} ${contact?.surname}"
            binding.tvNumber.text = contact?.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)

        holder.imageViewEdit.setOnClickListener {
            onEditButtonClicked(contact)
        }

    }

    fun setData(allContacts: List<ContactModel>) {
        this.submitList(allContacts)
        notifyDataSetChanged()
    }
}