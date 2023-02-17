package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.helloWorld.R
import com.demo.helloWorld.adapter.ParentNestedRecyclerViewAdapter
import com.demo.helloWorld.databinding.FragmentRecyclerViewBinding
import com.demo.helloWorld.records.StaticRecords


class NestedRecyclerViewFragment : Fragment() {

    lateinit var binding: FragmentRecyclerViewBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recTitleDesc.text = getString(R.string.nestedRecyclerViewDesc)

        binding.recView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.recView.adapter = ParentNestedRecyclerViewAdapter(requireActivity(), StaticRecords.generateAlphaWithCard())
    }


}