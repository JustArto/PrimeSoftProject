package com.example.primesoftproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ItemsDataInfo : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_data_info)
        textView = findViewById(R.id.text_desc)
        imageView = findViewById(R.id.imageView_desc)

        val image = intent.getStringExtra("DataImage")
        Glide.with(this).load(image)
            .apply(RequestOptions().centerCrop())
            .into(imageView)

        val desc = intent.getStringExtra("DataDesc")
        textView.text = desc



    }
}