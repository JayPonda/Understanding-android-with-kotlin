package com.demo.helloWorld.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.demo.helloWorld.R
import com.demo.helloWorld.data.Card

class CustomListViewAdapter(private var context: Context, private var list: Array<Card>) : BaseAdapter() {

    override fun getCount(): Int {
       return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val converted = convertView?:
                            LayoutInflater.from(context).inflate(R.layout.template_item, parent, false)
        val item = getItem(position) as Card

        converted?.findViewById<TextView>(R.id.listTitle)?.text = item.title.uppercase()
        converted?.findViewById<TextView>(R.id.listDescription)?.text = item.description
        if(converted?.findViewById<ImageView>(R.id.listImage) != null){ Log.i("Accept", "accept $position")}
        converted?.findViewById<ImageView>(R.id.listImage)?.setImageResource(item.imgRec)

        return converted
    }
}