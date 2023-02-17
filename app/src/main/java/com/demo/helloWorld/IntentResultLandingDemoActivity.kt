package com.demo.helloWorld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.demo.helloWorld.data.Student
import com.demo.helloWorld.databinding.ActivityIntentResultLandingDemoBinding

class IntentResultLandingDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntentResultLandingDemoBinding
    private lateinit var userName: String
    private lateinit var userAge: String

    private fun initVal(){
        val stdObj = intent.getSerializableExtra("UserText") as Student
        userAge = stdObj.age.toString()
        userName = stdObj.name

        binding.editName.setText(userName)
        binding.age.setText(userAge)

        Log.i("Int_Data", " $userName  $userAge")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentResultLandingDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVal()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.PreviousIntent.setOnClickListener {
            val age = binding.age.text.toString().trim()
            val name = binding.editName.text.toString().trim()

            if(age == "" || name == ""){
                Toast.makeText(this, "age and/or name are empty", Toast.LENGTH_LONG).show()
            }
            else if(userName == name && age == userAge){
                Toast.makeText(this, "make some changes to edit", Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent()
                intent.putExtra("Student_info", Student(name, age.toInt()))
                Log.i("Int_Data", "$name $userName $age $userAge")
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        binding.Cancle.setOnClickListener {
            setResult(RESULT_CANCELED)
            //onBackPressed()
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