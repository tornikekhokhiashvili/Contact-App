package com.example.contactapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.example.contactapp.Contact
import com.example.contactapp.R

class ContactListAdapter (
    private val context: Context,
    private val contacts: List<Contact>
): BaseAdapter() {
    override fun getCount(): Int {
        return contacts.size
    }

    override fun getItem(position: Int): Any {
        return contacts[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val contact = getItem(position) as Contact
        val inflater = LayoutInflater.from(context)
        val view: View
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_view, parent, false)
        } else {
            view = convertView
        }
        val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
        val phoneTextView = view.findViewById<TextView>(R.id.phoneTextView)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        nameTextView.text = contact.name
        phoneTextView.text = contact.phoneNumber
        if (!contact.photoUri.isNullOrEmpty()) {
            imageView.setImageURI(contact.photoUri.toUri())
        } else {
            imageView.setImageResource(R.drawable.default_contact_image)
        }


        return view
    }

}