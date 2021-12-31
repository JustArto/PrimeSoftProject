package com.example.primesoftproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.primesoftproject.model.Brand
import com.example.primesoftproject.model.Item

class RecyclerAdapterBrands(val context: Context) : RecyclerView.Adapter<RecyclerAdapterBrands.MyViewHolder>(){

    var brandItemsList: MutableList<Brand> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("TAGG", "size "+brandItemsList.size)
        return brandItemsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = brandItemsList[position]

        holder.tvMovieName.text = currentItem.name
        Glide.with(holder.itemView.context).load(currentItem.iconUrl)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListItemsForRecyclerAdapter(itemList: List<Brand>) {
        brandItemsList.clear()
        brandItemsList.addAll(itemList)
        notifyDataSetChanged()
    }


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val tvMovieName: TextView = itemView!!.findViewById(R.id.title)
        val image: ImageView = itemView!!.findViewById(R.id.image)

    }
}