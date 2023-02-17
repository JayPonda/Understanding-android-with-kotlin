package com.demo.helloWorld.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.helloWorld.R
import com.demo.helloWorld.data.Card

class ParentNestedRecyclerViewAdapter(private val ctx: Context, private val list: List<Pair<String, List<Card>>>):
    RecyclerView.Adapter<ParentNestedRecyclerViewAdapter.ParentHolder>() {

    class ParentHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.titleText)
        val childRecyclerView: RecyclerView = view.findViewById(R.id.childRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_parent_item, parent, false)
        return ParentHolder(view)
    }

    override fun onBindViewHolder(holder: ParentHolder, position: Int) {
        val map = list[position]
        holder.titleText.text = ctx.getString(R.string.textWithSize, map.first, map.second.size)
        holder.childRecyclerView.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
        holder.childRecyclerView.adapter = ChildNestedRecyclerViewAdapter(map.second)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}