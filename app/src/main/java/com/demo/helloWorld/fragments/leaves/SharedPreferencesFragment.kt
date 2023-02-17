package com.demo.helloWorld.fragments.leaves

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.helloWorld.R
import com.demo.helloWorld.databinding.FragmentSharedPreferencesBinding
import com.demo.helloWorld.fragments.navigation.MainActivityContent.Companion.NEW_VEL
import com.demo.helloWorld.fragments.navigation.MainActivityContent.Companion.SHARED_CONFIG

class SharedPreferencesFragment : Fragment() {

    lateinit var binding: FragmentSharedPreferencesBinding
    private var autoShow = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSharedPreferencesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireActivity().getSharedPreferences(SHARED_CONFIG, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.setAutoLoad.setOnCheckedChangeListener {
                _, isChecked -> autoShow = isChecked
            if(autoShow) binding.Show.performClick()
        }

        binding.Show.setOnClickListener {
            binding.previousWord.text = getString(R.string.previous_word_1_s, sharedPreferences.getString(
                NEW_VEL, "No Saved Text"))
        }

        binding.Save.setOnClickListener {
            editor.putString(NEW_VEL, binding.nextWord.text.toString())
            editor.apply()
            if(autoShow) binding.Show.performClick()
        }

        binding.delete.setOnClickListener {
            editor.remove(NEW_VEL)
            editor.apply()
        }

        binding.Show.performClick()
    }
}