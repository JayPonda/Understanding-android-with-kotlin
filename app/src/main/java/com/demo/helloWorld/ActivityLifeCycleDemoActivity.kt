package com.demo.helloWorld

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

import kotlin.system.exitProcess

class ActivityLifeCycleDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_demo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<TextView>(R.id.newText).text = getString(R.string.activity_life_cycle_title, "activity")

        Log.i("ALC_TAG", "onCreate")
        Toast.makeText(this, "create", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

        val alert = AlertDialog.Builder(this)
        alert.setMessage("Application status")
        alert.setPositiveButton("Run in background") { dialog, _ ->
            dialog.cancel()
            Toast.makeText(this, "Run in background", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME))
        }
        alert.setNegativeButton("go to previous") { _, _ ->
            super.onBackPressed()
            Toast.makeText(this, "Close and back", Toast.LENGTH_SHORT).show()
        }
        alert.setNeutralButton("Quit app") { _, _ ->
            Toast.makeText(this, "Quit app", Toast.LENGTH_SHORT).show()

            try {
                finishAffinity()
                Log.i("EXIT", "finishAffinity()")
            } catch (e: Exception) {
                Toast.makeText(this, "$e error occurred: try with other way", Toast.LENGTH_SHORT)
                    .show()
                try {
                    finishAndRemoveTask()
                    Log.i("EXIT", "finishAndRemoveTask()")
                    exitProcess(0)
                } catch (ex: Exception) {
                    Log.i("EXIT", "you should manually exit the app")
                    Toast.makeText(
                        this,
                        "$e and $ex error occurred: you should manually quit this app",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val showBox = alert.create()
        showBox.setTitle("Back or stop")
        showBox.show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show()
        Log.i("ALC_TAG", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "resume", Toast.LENGTH_SHORT).show()
        Log.i("ALC_TAG", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "restart", Toast.LENGTH_SHORT).show()
        Log.i("ALC_TAG", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show()
        Log.i("ALC_TAG", "onPause")

    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show()
        Log.i("ALC_TAG", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "destroy", Toast.LENGTH_SHORT).show()
        Log.i("ALC_TAG", "onDestroy")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onContextItemSelected(item)
    }
}