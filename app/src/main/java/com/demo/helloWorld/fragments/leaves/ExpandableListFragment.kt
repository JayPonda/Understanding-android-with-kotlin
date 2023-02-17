package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.demo.helloWorld.adapter.ExpandableListAdapter
import com.demo.helloWorld.data.Card
import com.demo.helloWorld.databinding.FragmentExpandableListBinding
import com.demo.helloWorld.records.StaticRecords

class ExpandableListFragment : Fragment() {
    
    lateinit var binding: FragmentExpandableListBinding
    private lateinit var detailedData : List<Pair<String, List<Card>>>
    private lateinit var adapter: ExpandableListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentExpandableListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailedData = StaticRecords.generateAlphaWithCard(1, 5, 1, 2)
        adapter = ExpandableListAdapter(requireActivity(), detailedData)

        binding.root.setAdapter(adapter)

        binding.root.setOnGroupExpandListener { groupPosition ->
            Toast.makeText(requireContext(),
                detailedData[groupPosition].first + " List Expanded.",
                Toast.LENGTH_SHORT).show()
        }

        binding.root.setOnGroupCollapseListener { groupPosition ->
            Toast.makeText(requireContext(),
                detailedData[groupPosition].first + " List Collapsed.",
                Toast.LENGTH_SHORT).show()
        }


        binding.root.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            Toast.makeText(requireContext(), detailedData[groupPosition].first
                    + " -> "
                    + detailedData[groupPosition].second[childPosition].title
                , Toast.LENGTH_SHORT
            ).show()
            false
        }
    }
}