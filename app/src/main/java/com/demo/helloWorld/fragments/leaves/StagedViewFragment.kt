package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.demo.helloWorld.MainActivity.Companion.VERSION
import com.demo.helloWorld.R
import com.demo.helloWorld.adapter.ChildNestedRecyclerViewAdapter
import com.demo.helloWorld.adapter.MultiViewRecyclerViewAdapter
import com.demo.helloWorld.adapter.RecyclerViewAdapter
import com.demo.helloWorld.data.Card
import com.demo.helloWorld.databinding.FragmentRecyclerViewBinding
import com.demo.helloWorld.records.StaticRecords


class StagedViewFragment : Fragment() {

    lateinit var binding: FragmentRecyclerViewBinding
    var version: Int = 0
    lateinit var list: Array<Card>


    private fun setElement(text: Int, layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<*>){
        binding.recTitleDesc.text = getString(text)
        binding.recView.layoutManager = layoutManager
        binding.recView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        version = arguments?.getInt(VERSION)?:version
        list = StaticRecords.multiplyCardList(3)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (version) {

            1 -> setElement(
                R.string.vStaged_desc,
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL),
                RecyclerViewAdapter(requireActivity(), list, 4)
            )
            2 -> setElement(
                R.string.gridLayoutDiscription,
                GridLayoutManager(requireActivity(), 2, GridLayoutManager.VERTICAL, false),
                RecyclerViewAdapter(requireActivity(), list, 4)
            )
            3 -> setElement(
                R.string.multiViewRecyclerViewDesc,
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false),
                MultiViewRecyclerViewAdapter(StaticRecords.generateMultiviewData(5))
            )
            else -> {
                binding.recView.layoutParams = ConstraintLayout.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT, // width
                    RecyclerView.LayoutParams.WRAP_CONTENT  // height
                )
                val constraint = ConstraintSet()

                with(constraint) {
                    clone(binding.root)
                    connect(binding.recView.id, ConstraintSet.TOP, binding.recTitleDesc.id, ConstraintSet.BOTTOM, 0)
                    applyTo(binding.root)
                }

                setElement(
                    R.string.vLinear_desc,
                    LinearLayoutManager( requireActivity(), LinearLayoutManager.HORIZONTAL, false),
                    ChildNestedRecyclerViewAdapter(
                        list.toList()
                    )
                )
            }
        }
    }
}