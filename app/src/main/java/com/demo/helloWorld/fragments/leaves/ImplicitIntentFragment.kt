package com.demo.helloWorld.fragments.leaves

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.helloWorld.databinding.FragmentImplicitBinding

class ImplicitIntentFragment : Fragment() {

    lateinit var binding: FragmentImplicitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentImplicitBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.OpenBrowser.setOnClickListener {
            val nextInt = Intent()
            nextInt.action = Intent.ACTION_MAIN
            nextInt.addCategory(Intent.CATEGORY_APP_BROWSER)
            nextInt.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            requireContext().startActivity(nextInt)
        }
    }
}