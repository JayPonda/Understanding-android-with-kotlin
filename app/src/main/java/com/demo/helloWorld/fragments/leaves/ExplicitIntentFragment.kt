package com.demo.helloWorld.fragments.leaves

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.demo.helloWorld.IntentLandingDemoActivity
import com.demo.helloWorld.IntentResultLandingDemoActivity
import com.demo.helloWorld.MainActivity.Companion.VERSION
import com.demo.helloWorld.data.Student
import com.demo.helloWorld.databinding.FragmentExplicitIntentBinding

class ExplicitIntentFragment : Fragment() {

    lateinit var binding: FragmentExplicitIntentBinding
    var version: Int = 0

    private val editedDataResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            results : ActivityResult? ->
        when (results?.resultCode) {
            AppCompatActivity.RESULT_OK -> {
                Toast.makeText(requireContext(), "data arrive", Toast.LENGTH_SHORT).show()
                try{
                    val editedData = results.data?.getSerializableExtra("Student_info") as Student
                    binding.editName.setText(editedData.name)
                    binding.age.setText(editedData.age.toString())
                } catch (e: Exception){
                    Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            AppCompatActivity.RESULT_CANCELED -> {
                Toast.makeText(requireContext(), "back pressed", Toast.LENGTH_SHORT).show()
            }
            else -> Toast.makeText(requireContext(), "UNEXPECTED RESULTS FOUND", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleNonResultIntent(std: Student) {
        val nextInt = Intent(requireContext(), IntentLandingDemoActivity::class.java)
        nextInt.putExtra("UserText", std)
        startActivity(nextInt)
    }

    private fun handleResultIntent(std: Student) {
        val newIntent = Intent(requireContext(), IntentResultLandingDemoActivity::class.java)
        newIntent.putExtra("UserText", std)
        Log.i("STD_INFO", "${std.age} ${std.name}")
        editedDataResult.launch(newIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        version = arguments?.getInt(VERSION)?:version
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentExplicitIntentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.OpenNewIntent.setOnClickListener {
            val age = binding.age.text.toString().trim()
            val name = binding.editName.text.toString().trim()
            if(age == "" || name == ""){
                Toast.makeText(requireContext(), "age and/or name are empty", Toast.LENGTH_LONG).show()
            }
            else{
                val std = Student(name, age.toInt())
                when (version) {
                    1 -> handleResultIntent(std)
                    else -> handleNonResultIntent(std)
                }

            }
        }
    }
}