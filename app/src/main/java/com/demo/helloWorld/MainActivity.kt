package com.demo.helloWorld

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.demo.helloWorld.databinding.ActivityMainBinding
import com.demo.helloWorld.records.StaticRecords.prepareCardList
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object{
        const val MENU_ID = "menu_id"
        const val MENU_STRING = "menu_string"
        const val VERSION = "version"
    }

    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.mainContent.toolbar)
        binding.navView.getHeaderView(0).findViewById<ImageButton>(R.id.goHome).visibility = View.GONE

        setContentView(binding.root)




        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.mainContent.toolbar, R.string.open_nav, R.string.close_nav)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        prepareCardList()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@MainActivity, DrawerActivity::class.java)
        intent.putExtra(MENU_ID, item.itemId)
        intent.putExtra(MENU_STRING, item.title)
        startActivity(intent)
        binding.drawerLayout.closeDrawer(GravityCompat. START )
        return true
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}