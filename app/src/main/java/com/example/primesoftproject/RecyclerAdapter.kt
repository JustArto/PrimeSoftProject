package com.example.primesoftproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.primesoftproject.model.Item
import java.util.*

class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private var brandItemsList: MutableList<Item> = mutableListOf()
    private var changedData: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "size: " + changedData.size)
        return changedData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = changedData[position]

        holder.tvMovieName.text = currentItem.name
        Glide.with(holder.itemView.context).load(currentItem.iconUrl)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ItemsDataInfo::class.java).apply {
                putExtra("DataName", brandItemsList[position].name)
                putExtra("DataPrice", brandItemsList[position].price.toString())
                putExtra("DataDesc", brandItemsList[position].description)
                putExtra("DataImage", brandItemsList[position].iconUrl)
            }

            holder.itemView.context.startActivity(intent)
        }
        /*   holder.itemView.setOnClickListener {
               val intent = Intent(holder.itemView.context,MainActivity::class.java)
               intent.putExtra("DataList","${brandItemsList[position].name}")
               holder.itemView.context.startActivity(intent)
           }*/
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListItemsForRecyclerAdapter(itemList: List<Item>) {
        brandItemsList.clear()
        brandItemsList.addAll(itemList)
        changedData.addAll(brandItemsList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterDataBySearch(queryText: String) {
        changedData.clear()
        if (queryText.isNotEmpty()) {
            val filteredData =
                brandItemsList.filter {
                    it.name.contains(queryText, ignoreCase = true)
                            && it.description.contains(queryText, ignoreCase = true)
                }
                    .toMutableList()
            changedData.addAll(filteredData)
            notifyDataSetChanged()
        } else {
            changedData.addAll(brandItemsList)
            notifyDataSetChanged()
        }
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val tvMovieName: TextView = itemView!!.findViewById(R.id.title)
        val image: ImageView = itemView!!.findViewById(R.id.image)
    }

    companion object {
        private val TAG = RecyclerAdapter::class.java.simpleName
    }
}