package com.example.egreetings.MyAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_greetings.CategoryActivity
import com.example.egreetings.Model.Dashboard_Model
import com.example.egreetings.R
import com.example.egreetings.other.Apiinterface
import com.squareup.picasso.Picasso

class MyAdapter(context: Context, dataList: List<Dashboard_Model>) :
    RecyclerView.Adapter<MyAdapter.Myview>() {
    var context: Context
    var dataList: List<Dashboard_Model>
    var api: Apiinterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview
    {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.desigb_dashboard, parent, false)
        return Myview(view)
    }

    override fun onBindViewHolder(holder: Myview, @SuppressLint("RecyclerView") position: Int)
    {
        holder.t2.setText(dataList[position].c_name)
        Picasso.get().load(dataList[position].c_image).into(holder.img)
        holder.img.setOnClickListener()
        {
            var i =Intent(context, CategoryActivity::class.java)
            i.putExtra("MyPos",position)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class Myview(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView
        var t2: TextView


        init {
            img = itemView.findViewById(R.id.img)
            t2 = itemView.findViewById(R.id.txt)

        }
    }

    init {
        this.context = context
        this.dataList = dataList
    }
}
