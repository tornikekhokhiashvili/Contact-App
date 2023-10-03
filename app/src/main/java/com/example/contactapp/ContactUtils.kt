package com.example.contactapp

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.provider.ContactsContract

object ContactUtils {
    val contactsList = mutableListOf<Contact>()
    @SuppressLint("Range")
    fun readContacts(contentResolver: ContentResolver): List<Contact> {
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.PHOTO_URI
        )

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.use { data ->
            while (data.moveToNext()) {
                val name = data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNumber = data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val photoUri = data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))

                val contact = Contact(name, phoneNumber, photoUri)
                contactsList.add(contact)
            }
        }

        return contactsList
    }
}