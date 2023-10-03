package com.example.contactapp.adapter
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.Contact
import com.example.contactapp.R

class ContactRecyclerAdapter(
    private val context: Context,
    private val contacts: List<Contact>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(contact: Contact)
    }
    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val phoneTextView: TextView = itemView.findViewById(R.id.phoneTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactRecyclerAdapter.ContactViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactRecyclerAdapter.ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.name
        holder.phoneTextView.text = contact.phoneNumber
        if (!contact.photoUri.isNullOrEmpty()) {
            holder.imageView.setImageURI(Uri.parse(contact.photoUri))
        } else {
            holder.imageView.setImageResource(R.drawable.default_contact_image)
        }
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(contact)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

}