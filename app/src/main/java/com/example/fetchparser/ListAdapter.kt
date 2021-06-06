package com.example.fetchparser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(context: Context, itemList: ArrayList<Item>): BaseAdapter()
{
    private val inflater: LayoutInflater
    private val itemList:ArrayList<Item>

    init{
        this.inflater = LayoutInflater.from(context)
        this.itemList = itemList
    }

    override fun getCount():Int{
        return itemList.size
    }

    override fun getItem(position:Int): Any{
        return itemList.get(position)
    }

    override fun getItemId(position: Int):Long{
        return position.toLong()
    }
    
    override fun getView(position:Int, convertView: View?, parent: ViewGroup):View?{
        val view:View?
        val listRowHolder: ListRowHolder

        if(convertView == null){
            view = this.inflater.inflate(R.layout.list_layout, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder

        }
        else{
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }
        listRowHolder.listId.text = itemList.get(position).listId.toString()
        listRowHolder.name.text = itemList.get(position).name
        listRowHolder.id.text = itemList.get(position).id.toString()
        return view

    }

    private class ListRowHolder(row:View){
        val listId = row?.findViewById(R.id.itemIdView) as TextView
        val name = row?.findViewById(R.id.nameView) as TextView
        val id = row?.findViewById(R.id.idView) as TextView
    }

}