package com.example.primesoftproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


        val apiInterface = ApiInterface.create().getMems()

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<List<Meme>> {
            override fun onResponse(call: Call<List<Meme>>?, response: Response<List<Meme>>?) {
                Log.d("MyTag", "Success")
                if(response?.body() != null)
                    recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<Meme>>?, t: Throwable?) {
            Log.d("MyTag2", "Failure "+t.toString())
            }
        })
    }
}