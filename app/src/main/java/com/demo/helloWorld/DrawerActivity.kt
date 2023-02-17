package com.demo.helloWorld

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.demo.helloWorld.MainActivity.Companion.MENU_ID
import com.demo.helloWorld.MainActivity.Companion.MENU_STRING
import com.demo.helloWorld.MainActivity.Companion.VERSION
import com.demo.helloWorld.databinding.ActivityDrawerBinding
import com.demo.helloWorld.fragments.navigation.MainActivityContent
import com.demo.helloWorld.fragments.navigation.MainActivityContent.Companion.ENABLE_HOME_WITH_BACK
import com.demo.helloWorld.fragments.navigation.MainActivityContent.Companion.SHARED_CONFIG
import com.google.android.material.navigation.NavigationView
import com.demo.helloWorld.handlers.navigationAction.NavigationActionRecord.getObject
import kotlin.reflect.KClass

class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityDrawerBinding
    private var intentVal: Int = -1
    private val myBundle = Bundle()
    var version: Int = -1
        private set(value){
            field = value
            myBundle.putInt(VERSION, field)
        }
    private lateinit var sharedPreferences: SharedPreferences

    private fun setMenu(intExtra: Int) {
        intentVal = intExtra
        val menu = when(intentVal){
            R.id.intent_demo -> R.menu.menu_intent
            R.id.notification_demo -> R.menu.menu_notification
            R.id.alert_box_demo -> R.menu.menu_alertbox
            R.id.bottom_sheet_demo -> R.menu.menu_bottomshit
            R.id.scrollable_layouts_demo -> R.menu.menu_scrollables
            R.id.background_async_task_demo -> R.menu.menu_bgandasync
            R.id.storage_demo -> R.menu.menu_datastorage
            else -> R.menu.menu_home
        }
        binding.navView.inflateMenu(menu)
    }

    private fun <T : Fragment> switch(fragment: KClass<T>, bundle: Bundle? = null){
        supportFragmentManager.commit {
            replace(binding.mainContent.fragmentSpace.id, fragment.java, bundle)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun setFragment(itemId: Int){
        val expandedMenuItemModel = getObject(itemId)
        if(expandedMenuItemModel.isFragment){
            if(expandedMenuItemModel.version != -1){
                version = expandedMenuItemModel.version
                switch(expandedMenuItemModel.menuFragment as KClass<out Fragment>, myBundle)
            }
            else
                switch(expandedMenuItemModel.menuFragment as KClass<out Fragment>)
        }
        else{
            val outIntent = Intent(this, expandedMenuItemModel.menuFragment.java)
            startActivity(outIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = this.getSharedPreferences(SHARED_CONFIG, Context.MODE_PRIVATE)

        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setMenu(intent.getIntExtra(MENU_ID, -1))
        setSupportActionBar(binding.mainContent.toolbar)
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.descOfHeader).text = intent.getCharSequenceExtra(
            MENU_STRING)
        binding.navView.getHeaderView(0).findViewById<ImageButton>(R.id.goHome).setOnClickListener {
            finish()
        }
        binding.drawerLayout.openDrawer(GravityCompat.START)
        switch(MainActivityContent::class)

        setContentView(binding.root)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.mainContent.toolbar, R.string.open_nav, R.string.close_nav)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        setFragment(item.itemId)
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else{
            if(sharedPreferences.getBoolean(ENABLE_HOME_WITH_BACK, false))
                finish()
            else
                super.onBackPressed()
        }
    }
}
