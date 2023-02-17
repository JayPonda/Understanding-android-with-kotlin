package com.demo.helloWorld.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.demo.helloWorld.R
import com.demo.helloWorld.data.Card

class CustomListViewWithViewHolderPatternAdapter(private var context: Context, private var list: Array<Card>) : BaseAdapter() {

    class CardsViewHolder(view: View){
        var limg: ImageView
        var ltitle: TextView
        var ldesc: TextView

        init {
            limg = view.findViewById(R.id.listImage)
            ltitle = view.findViewById(R.id.listTitle)
            ldesc = view.findViewById(R.id.listDescription)
        }
    }

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

        val converted: View
        val cardsViewHolder : CardsViewHolder

        if(convertView == null){
            converted = LayoutInflater.from(context).inflate(R.layout.template_item, parent, false)
            cardsViewHolder = CardsViewHolder(converted)
            converted.tag = cardsViewHolder
        }
        else{
            converted = convertView
            cardsViewHolder = converted.tag as CardsViewHolder
        }

        val item : Card = getItem(position) as Card
        cardsViewHolder.limg.setImageResource(item.imgRec)
        cardsViewHolder.ltitle.text = item.title
        cardsViewHolder.ldesc.text = item.description

        return converted
    }
}