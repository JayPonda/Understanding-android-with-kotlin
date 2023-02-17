package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.helloWorld.MainActivity.Companion.VERSION
import com.demo.helloWorld.adapter.CustomListViewAdapter
import com.demo.helloWorld.adapter.CustomListViewWithViewHolderPatternAdapter
import com.demo.helloWorld.databinding.FragmentBaseAdapterDemoBinding
import com.demo.helloWorld.records.StaticRecords

class BaseAdapterDemoFragment : Fragment() {

    lateinit var binding: FragmentBaseAdapterDemoBinding
    var version = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        version = requireArguments().getInt(VERSION, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBaseAdapterDemoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterSpecificDescription: String
        val cardList = StaticRecords.multiplyCardList(1)

        if(version == 0){
            adapterSpecificDescription = "\nThis List contain view holder pattern for smooth scrolling and memory optimization"
            binding.mylist.adapter = CustomListViewWithViewHolderPatternAdapter (requireActivity(), cardList)
        }
        else{
            adapterSpecificDescription = "\nThis List implemented with BaseAdapter abstract class as listView.adapter"
            binding.mylist.adapter = CustomListViewAdapter(requireActivity(), cardList)
        }

        binding.TypeInfo.text = adapterSpecificDescription
    }

}