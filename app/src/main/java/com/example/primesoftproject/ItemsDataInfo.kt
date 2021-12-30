package com.example.primesoftproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ItemsDataInfo : AppCompatActivity() {
    lateinit var textName: TextView
    lateinit var textDesc: TextView
    lateinit var textPrice: TextView
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_data_info)

        textName = findViewById(R.id.text_name)
        textPrice = findViewById(R.id.text_price)
        textDesc = findViewById(R.id.text_desc)
        imageView = findViewById(R.id.imageView_desc)

        val image = intent.getStringExtra("DataImage")
        Glide.with(this).load(image)
            .apply(RequestOptions().centerCrop())
            .into(imageView)

        val name = intent.getStringExtra("DataName")
        textName.text ="Name: "+name

        val price = intent.getStringExtra("DataPrice")
        textPrice.text ="Price: "+price

        val desc = intent.getStringExtra("DataDesc")
        textDesc.text ="Desc: "+desc



    }
}