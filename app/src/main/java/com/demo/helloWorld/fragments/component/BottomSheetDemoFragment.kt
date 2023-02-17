package com.demo.helloWorld.fragments.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.helloWorld.records.StaticRecords.multiplyCardList
import com.demo.helloWorld.databinding.FragmentBottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDemoFragment : BottomSheetDialogFragment() {

    private var index: Int = 0

    private lateinit var binding: FragmentBottomSheetLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        val list = multiplyCardList(1)

        binding.template1.listImage.setImageResource(list[index].imgRec)
        binding.template1.listTitle.text = list[index].title
        binding.template1.listDescription.text = list[index].description

        index = if(index + 1 == list.size) 0 else index + 1
        binding.template2.listImage.setImageResource(list[index].imgRec)
        binding.template2.listTitle.text = list[index].title
        binding.template2.listDescription.text = list[index].description

    }
}