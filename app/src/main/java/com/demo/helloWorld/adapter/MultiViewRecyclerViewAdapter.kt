package com.demo.helloWorld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.helloWorld.R
import com.demo.helloWorld.data.LayoutType
import com.demo.helloWorld.data.MultiviewData

class MultiViewRecyclerViewAdapter(val list: List<MultiviewData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class VersionOneHandler(view: View): RecyclerView.ViewHolder(view){
        // layout file name cardview_item_template
        val img: ImageView = view.findViewById(R.id.recyclerImage)
        val childTitle: TextView = view.findViewById(R.id.recyclerTitle)
        val childIcecreamDescription: TextView = view.findViewById(R.id.recyclerDesc)
    }

    inner class VersionTwoHandler(view: View): RecyclerView.ViewHolder(view){
        // layout file name old_cardview_item_template
        val img: ImageView = view.findViewById(R.id.recyclerImage)
        val childTitle: TextView = view.findViewById(R.id.recyclerTitle)
        val childIcecreamDescription: TextView = view.findViewById(R.id.recyclerDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            LayoutType.VERSION_ONE.n -> {
                VersionOneHandler(
                    LayoutInflater.from(parent.context).inflate(R.layout.template_cardview_item, parent, false)
                )
            }
            else -> {
                VersionTwoHandler(
                    LayoutInflater.from(parent.context).inflate(R.layout.template_old_cardview_item, parent, false)
                )

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when(item.layoutType){
            LayoutType.VERSION_ONE -> {
                val info = item.layout
                (holder as VersionOneHandler).img.setImageResource(info.imgRec)
                holder.childTitle.text = info.title
                holder.childIcecreamDescription.text = info.description
            }
            else -> {
                val info = item.layout
                (holder as VersionTwoHandler).img.setImageResource(info.imgRec)
                holder.childTitle.text = info.title
                holder.childIcecreamDescription.text = info.description
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].layoutType.n
    }


}