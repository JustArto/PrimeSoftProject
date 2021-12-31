package com.example.primesoftproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.primesoftproject.model.Brand
import com.example.primesoftproject.model.Item
import com.example.primesoftproject.model.JsonMainCreated
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrandsActivity : AppCompatActivity() {
    lateinit var data: MutableList<Brand>
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapterBrands

    //lateinit var brandItemViewModel: BrandItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerAdapter = RecyclerAdapterBrands(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        val apiInterface = ApiInterface.create().getBrandData("en")

        apiInterface.enqueue( object : Callback<JsonMainCreated> {
            override fun onResponse(call: Call<JsonMainCreated>?, response: Response<JsonMainCreated>?) {
                Log.d("TAGG", "Success")
                if(response?.body() != null)
                    data = response.body()?.result?.data?.brands as MutableList<Brand>
                recyclerAdapter.setListItemsForRecyclerAdapter(data)
                Log.d("TAGG", "Success but null?! "+response?.body().toString())
            }

            override fun onFailure(call: Call<JsonMainCreated>?, t: Throwable?) {
                Log.d("TAGG", "Failure "+t.toString())
            }
        })

    }
}