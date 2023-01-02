package com.example.egreetings.MyAdapter


import android.annotation.SuppressLint
import android.content.Context

import androidx.recyclerview.widget.RecyclerView

import android.content.Intent

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import com.example.egreetings.FullScreenActivity
import com.example.egreetings.Model.CategoryModel
import com.example.egreetings.R
import com.example.egreetings.other.Apiinterface
import com.squareup.picasso.Picasso


class MyAdapter2(context: Context, dataList: List<CategoryModel>) : RecyclerView.Adapter<MyAdapter2.Myview>() {
    var context: Context
    var dataList: List<CategoryModel>
    var api: Apiinterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview
    {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.design_category, parent, false)
        return Myview(view)
    }

    override fun onBindViewHolder(holder: Myview, @SuppressLint("RecyclerView") position: Int)
    {

        Picasso.get().load(dataList[position].image).into(holder.img)
        holder.img.setOnClickListener()
        {
            var i =Intent(context, FullScreenActivity::class.java)
            i.putExtra("image",dataList[position].image)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class Myview(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView



        init {
            img = itemView.findViewById(R.id.img)


        }
    }

    init {
        this.context = context
        this.dataList = dataList
    }
}