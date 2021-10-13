package com.example.quizapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class StatAdapter(
    private val context: Context,
    private val AllStat: ArrayList<StatFeed>
):BaseAdapter() {
    override fun getCount(): Int {
       return AllStat.count()
    }

    override fun getItem(p0: Int): Any {
         return p0.toLong()
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
       val layoutInflater:LayoutInflater= LayoutInflater.from(context)
        val mainRow=layoutInflater.inflate(R.layout.itemlist,viewGroup,false)

        val statText:TextView=mainRow.findViewById(R.id.stat_text)
        val statimg:ImageView=mainRow.findViewById(R.id.stat_image)
        statText.text=AllStat[position].name
        statimg.setImageResource(AllStat[position].image)





        return mainRow
    }

}
