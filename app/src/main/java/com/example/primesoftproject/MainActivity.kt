package com.example.primesoftproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.primesoftproject.model.Data
import com.example.primesoftproject.model.Item
import com.example.primesoftproject.model.JsonMainCreated
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerAdapter = RecyclerAdapter(this)

        //recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter


        val apiInterface = ApiInterface.create().getBrandData()

        apiInterface.enqueue( object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>?, response: Response<List<Item>>?) {
                Log.d("TAGG", "Success")
                if(response?.body() != null)
                    recyclerAdapter.setMovieListItems(response.body()!!)
                Log.d("TAGG", "Success but null?! "+response!!.body().toString())
            }

            override fun onFailure(call: Call<List<Item>>?, t: Throwable?) {
            Log.d("TAGG", "Failure "+t.toString())
            }
        })
    }
}