package com.example.quizapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.DbQuerry
import com.example.quizapp.Models.CategoryModel
import com.example.quizapp.R
import com.example.quizapp.TestActivity


class CategoryAdapter(var context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {



    var dataList = emptyList<CategoryModel>()

    internal fun setDataList(dataList: List<CategoryModel>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var docId: TextView
        var noOftests: TextView
        var my_category_layout: CardView

        init {
            name = itemView.findViewById(R.id.docId)
            docId = itemView.findViewById(R.id.categoryName)
            noOftests = itemView.findViewById(R.id.noOfTests)
            my_category_layout = itemView.findViewById(R.id.my_category_layout)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_item_layout, parent, false)


//     view.setOnClickListener {
//
//         DbQuerry.g_selected_cat_Index = i
//
//         val intent = Intent(view.context,TestActivity::class.java)
////              intent.putExtra("category", )
//         view.context.startActivity(intent)
//     }


        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.docId.text = data.docId
        holder.name.text = data.name
        holder.noOftests.text = data.noOfTests.toString()

        holder.my_category_layout.setOnClickListener {

            DbQuerry.g_selected_cat_Index = position

            val intent = Intent(context, TestActivity::class.java)
            intent.putExtra("category", data.name)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}