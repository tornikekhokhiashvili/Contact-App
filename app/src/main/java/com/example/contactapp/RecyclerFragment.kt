package com.example.contactapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.adapter.ContactRecyclerAdapter

class RecyclerFragment : Fragment(),ContactRecyclerAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_recycler, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerview)
        ContactUtils.readContacts(requireContext().contentResolver)
        val adapter = ContactRecyclerAdapter(requireContext(), ContactUtils.contactsList,this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        return rootView
    }

    override fun onItemClick(contact: Contact) {
        if (contact.phoneNumber.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${contact.phoneNumber}")
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "No phone number available", Toast.LENGTH_SHORT).show()
        }
    }
}