package com.example.primesoftproject

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.primesoftproject.model.JsonMainCreated

class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    var itemBrandList : List<JsonMainCreated> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("TAGG", "size "+itemBrandList.size)
        return itemBrandList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvMovieName.text = itemBrandList.get(position).result.data.items.get(position).name
        Glide.with(context).load(itemBrandList.get(position).result.data.items.get(position).iconUrl)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieListItems(itemList: List<JsonMainCreated>){
        this.itemBrandList = itemList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val tvMovieName: TextView = itemView!!.findViewById(R.id.title)
        val image: ImageView = itemView!!.findViewById(R.id.image)
    }




}