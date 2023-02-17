package com.demo.helloWorld.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.demo.helloWorld.R
import com.demo.helloWorld.data.Card
import java.util.*


class ExpandableListAdapter(
    private val context: Context,
    private val expandableListDetail: List<Pair<String, List<Card>>>,
): BaseExpandableListAdapter() {



    override fun getGroupCount(): Int {
        return expandableListDetail.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return expandableListDetail[groupPosition].second.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return expandableListDetail[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return  expandableListDetail[groupPosition].second[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {

        val listTitle = getGroup(groupPosition) as Pair<*, *>
        var newConvertView = convertView
        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newConvertView = layoutInflater.inflate(R.layout.template_group_list, parent, false)
        }

        val listTitleTextView = newConvertView!!.findViewById(R.id.listTitle) as TextView
        val listViewImg = newConvertView.findViewById<ImageView>(R.id.img)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = (listTitle.first as String).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
        }


        if(isExpanded){
            listViewImg.setImageResource(R.drawable.drop_down)
        }
        else{
            listViewImg.setImageResource(R.drawable.drop_up)
        }
        return newConvertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val expandedListText = getChild(groupPosition, childPosition) as Card
        var newConvertView = convertView
        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newConvertView = layoutInflater.inflate(R.layout.template_child_list , parent, false)
        }

        val title = newConvertView!!.findViewById(R.id.expandedListItem) as TextView
        val desc = newConvertView.findViewById(R.id.expandedListDesc) as TextView
        desc.text = expandedListText.description
        title.text = expandedListText.title
        return newConvertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}