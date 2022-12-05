package com.example.allapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

internal class GridAdapter(
    private val listApp: MutableList<App>,
    private val context: Context
):BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var appName: TextView
    private lateinit var appIcon: ImageView
    override fun getCount(): Int {
        return listApp.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.grid_item, null)
        }
        appName = convertView!!.findViewById(R.id.appName)
        appIcon = convertView!!.findViewById(R.id.appIcon)
        appIcon.setImageDrawable(listApp.get(position).icon)
        appName.setText(listApp.get(position).appName)
        return convertView
    }

}