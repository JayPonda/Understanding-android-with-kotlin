package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import com.demo.helloWorld.databinding.FragmentPersistentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PersistentBottomSheetFragment : Fragment() {

    lateinit var binding: FragmentPersistentBottomSheetBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<NestedScrollView>
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPersistentBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = binding.state
        bottomSheetBehavior = BottomSheetBehavior.from(binding.nestedScrollView)

        binding.expand.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.collapse.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        bottomSheetBehavior.addBottomSheetCallback(object:
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                textView.text = when(newState){
                    BottomSheetBehavior.STATE_COLLAPSED -> "STATE COLLAPSED"
                    BottomSheetBehavior.STATE_EXPANDED -> "STATE EXPANDED"
                    BottomSheetBehavior.STATE_DRAGGING -> "STATE DRAGGING"
                    BottomSheetBehavior.STATE_SETTLING -> "STATE SETTLING"
                    BottomSheetBehavior.STATE_HIDDEN -> "STATE HIDDEN"
                    else -> "STATE UNDEFINED"
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                //
            }
        })
    }
}