package com.example.ahorra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class ExpandibleListViewAdapter internal constructor(private val context: Context, private val listado: List<String>, private  val topicList: HashMap<String, List<String>>):
    BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return listado.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.topicList[this.listado[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return listado[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.topicList[this.listado[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {

        var convertView = convertView
        val chapterTitle = getGroup(groupPosition) as String

        if (convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.no_eliminar_lista_desplegable_padre_acerca_de_nosotros, null)
        }
        val chapterTv = convertView!!.findViewById<TextView>(R.id.chapter_tv)
        chapterTv.setText(chapterTitle)

        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val topicTitle = getChild(groupPosition, childPosition) as String

        if (convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.no_eliminar_listado_acercadenosotros, null)
        }
        val topicList = convertView!!.findViewById<TextView>(R.id.chapter_tv)
        topicList.setText(topicTitle)

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}