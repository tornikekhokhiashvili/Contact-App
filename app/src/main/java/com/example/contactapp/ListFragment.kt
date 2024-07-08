package com.example.contactapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.contactapp.adapter.ContactListAdapter

class ListFragment : Fragment() {
    private lateinit var listView: ListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)
        listView = rootView.findViewById(R.id.listview)
        ContactUtils.readContacts(requireContext().contentResolver)
        val adapter = ContactListAdapter(requireContext(), ContactUtils.contactsList.distinct())
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, i, _ ->
            val clickedContact = ContactUtils.contactsList[i]
            if (clickedContact.phoneNumber.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${clickedContact.phoneNumber}")
                startActivity(intent)
            }
        }
        return rootView
    }
}