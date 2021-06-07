package com.example.stockerr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawer: DrawerLayout
    private lateinit var tools: Tools
    private val fragmentManager = supportFragmentManager
    private lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        tools = Tools(this)
        drawer = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragments_frame, HomeFrag(this, tools))
        fragmentTransaction.commit()


        navView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.home) {
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragments_frame, HomeFrag(this, tools)).addToBackStack("home")
                fragmentTransaction.commit()
            }
            else if (menuItem.itemId == R.id.bmp) {
                toolbar.title = "After Bonus Market Price"
                val fragmentTransaction1 = fragmentManager.beginTransaction()
                fragmentTransaction1.replace(R.id.fragments_frame, AfterBonusPriceFragment(this, tools)).addToBackStack("home")
                fragmentTransaction1.commit()
            }

            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}