package com.example.quizapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.Adapters.TestAdapter.ViewHolder
import com.example.quizapp.Models.TestModel
import com.example.quizapp.R
import com.example.quizapp.StartTestActivity




class TestAdapter(var context: Context, myList: List<TestModel>?, var category: String?):RecyclerView.Adapter<ViewHolder>() {


    private var testList = myList
    private var myCategoryName = category

    internal fun setDataTestList(testList: List<TestModel>) {
        this.testList = testList
    }


    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        var testNo :TextView
        var progressBar : ProgressBar
        var topScore : TextView
        var myCardViewToShowPercentage: CardView? = null

        init {

            testNo=itemView.findViewById(R.id.tv_title)
            topScore=itemView.findViewById(R.id.scoretext)
            progressBar=itemView.findViewById(R.id.progressbar_test)
            myCardViewToShowPercentage = itemView.findViewById(R.id.user_test_percentage_layout)
        }



    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = testList!![position]

       // Toast.makeText(context, "Inside OnBinde" +data.time.toInt(), Toast.LENGTH_LONG).show()

        holder.testNo.text = data.testId

        holder.progressBar.progress = data.topScore.toInt()
        //holder.progressBar.progress = data.time.toInt()
        holder.topScore.text = data.topScore.toString()

        holder.myCardViewToShowPercentage!!.setOnClickListener(View.OnClickListener {

            Toast.makeText(context, ""+data.topScore, Toast.LENGTH_LONG).show()

            val intent = Intent(context, StartTestActivity::class.java)
            intent.putExtra("categoryName", myCategoryName)
            intent.putExtra("myScore", data.topScore)
            intent.putExtra("testNo", data.testId)
            context.startActivity(intent)
           // (context as Activity).finish()
        })
    }



    override fun getItemCount(): Int {
        return testList!!.size
    }

}