package com.example.contactapp

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)
        init()
        requestContactPermission()
        setSupportActionBar(toolbar)
        val toggle=ActionBarDrawerToggle(this,drawerLayout,
            toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigation()
    }
    private fun requestContactPermission() {
        val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                ContactUtils.readContacts(contentResolver)
            } else {
                Toast.makeText(this, "Contacts permission required for the app", Toast.LENGTH_SHORT).show()
            }
        }

        permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
    }
    private fun init(){
        toolbar=findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
    }
    private fun navigation (){
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_listview -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.ListViewContainer, ListFragment())
                        .commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_recyclerview -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.RecyclerViewContainer, RecyclerFragment())
                        .commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }
    }
}