package com.demo.helloWorld.fragments.navigation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.helloWorld.databinding.FragmentMainActivityContentBinding

class MainActivityContent : Fragment() {

    companion object{
        const val SHARED_CONFIG = "sharedConfig"
        const val ENABLE_HOME_WITH_BACK = "enable home with back"
        const val NEW_VEL = "newVal"
    }

    private lateinit var binding: FragmentMainActivityContentBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences(SHARED_CONFIG, Context.MODE_PRIVATE)
        if(!sharedPreferences.contains(ENABLE_HOME_WITH_BACK)){
            with(sharedPreferences.edit()){
                putBoolean(ENABLE_HOME_WITH_BACK, true)
                commit()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainActivityContentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.config1.setOnCheckedChangeListener { _, isChecked ->
            with(sharedPreferences.edit()){
                putBoolean(ENABLE_HOME_WITH_BACK, isChecked)
                apply()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.config1.isChecked = sharedPreferences.getBoolean(ENABLE_HOME_WITH_BACK, false)
    }
}