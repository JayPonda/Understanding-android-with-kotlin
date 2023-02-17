package com.demo.helloWorld.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.demo.helloWorld.R
import com.demo.helloWorld.data.Card

class RecyclerViewAdapter(private val ctx: Context, private val list: Array<Card>, private val version: Int) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {

    private var statusId = 0

    inner class ViewHolderClass(view: View) : RecyclerView.ViewHolder(view){
        var img: ImageView = view.findViewById(R.id.recyclerImage)
        var title : TextView = view.findViewById(R.id.recyclerTitle)
        var desc : TextView = view.findViewById(R.id.recyclerDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        statusId++
        Log.i("BIND_VIEW", statusId.toString())
        val crdView = LayoutInflater.from(ctx).inflate(
            when (version) {
                1 -> R.layout.template_old_cardview_item
                2 -> R.layout.template_cardview_item
                3 -> R.layout.template_child_item
                4 -> R.layout.template_item_new
                else -> R.layout.template_old_cardview_item
            },
            parent , false)

        return ViewHolderClass(crdView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val title = list[position].title
        holder.img.setImageResource(list[position].imgRec)
        holder.title.text = title
        holder.desc.text = list[position].description
        holder.img.setOnClickListener{
           Toast.makeText(ctx, title, Toast.LENGTH_SHORT).show()
        }
        statusId++
        Log.i("BIND_VIEW", "$statusId $title")
    }

    override fun getItemCount(): Int {
        return list.size
    }
}