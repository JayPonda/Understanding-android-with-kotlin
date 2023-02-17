package com.demo.helloWorld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.demo.helloWorld.data.Student
import com.demo.helloWorld.databinding.ActivityIntentLandingBinding

class IntentLandingDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntentLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val studentObject = intent.getSerializableExtra("UserText") as Student
        val textString = "name: ${studentObject.name} \nage: ${studentObject.age}"
        binding.newIntent.text = textString

        binding.goToHome.setOnClickListener {
            finish()
        }
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