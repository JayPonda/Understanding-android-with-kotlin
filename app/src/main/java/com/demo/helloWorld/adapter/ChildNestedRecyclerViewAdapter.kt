package com.demo.helloWorld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.helloWorld.R
import com.demo.helloWorld.data.Card

class ChildNestedRecyclerViewAdapter(private val list: List<Card>): RecyclerView.Adapter<ChildNestedRecyclerViewAdapter.ChildHandler>() {

    class ChildHandler(view: View): RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.recyclerImage)
        val childTitle: TextView = view.findViewById(R.id.recyclerTitle)
        val childIceCreamDescription: TextView = view.findViewById(R.id.recyclerDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHandler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_child_item, parent, false)
        return ChildHandler(view)
    }

    override fun onBindViewHolder(holder: ChildHandler, position: Int) {
        val card = list[position]
        holder.img.setImageResource(card.imgRec)
        holder.childTitle.text = card.title
        holder.childIceCreamDescription.text = card.description
    }

    override fun getItemCount(): Int {
        return list.size
    }
}


