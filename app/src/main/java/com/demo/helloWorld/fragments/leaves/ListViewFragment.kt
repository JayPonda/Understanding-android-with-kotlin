package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.demo.helloWorld.databinding.FragmentListViewBinding
import com.demo.helloWorld.records.StaticRecords


class ListViewFragment : Fragment() {

    lateinit var binding: FragmentListViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val customAdapter = ArrayAdapter(requireActivity(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            StaticRecords.multiplyAlphaList(1))

        binding.root.adapter = customAdapter

        binding.root.setOnItemClickListener { _, _, i, _ -> Toast.makeText(requireActivity(), customAdapter.getItem(i), Toast.LENGTH_SHORT).show() }
    }
}