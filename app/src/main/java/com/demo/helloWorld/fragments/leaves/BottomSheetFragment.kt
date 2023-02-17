package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.helloWorld.databinding.FragmentBottomSheet2Binding
import com.demo.helloWorld.fragments.component.BottomSheetDemoFragment

class BottomSheetFragment : Fragment() {

    lateinit var binding: FragmentBottomSheet2Binding
    private lateinit var bFragment: BottomSheetDemoFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBottomSheet2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bFragment = BottomSheetDemoFragment()

        binding.openSheet.setOnClickListener {
            activity?.let {
                    it1 -> bFragment.show(it1.supportFragmentManager , bFragment.tag)
            }
        }
    }
}