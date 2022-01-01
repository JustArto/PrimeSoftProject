package com.example.primesoftproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.primesoftproject.model.JsonMainCreated
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {


    private val bottomNav: BottomNavigationView by lazy { findViewById(R.id.bottomNavigationView) }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerview) }
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                //R.id.menu_list_button-> {onBackPressed()}
                R.id.menu_brand_button -> {
                    val intentBrand = Intent(this@MainActivity, BrandsActivity::class.java)
                    startActivity(intentBrand)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        loadBrandsItem()
    }

    private fun loadBrandsItem() {
        val apiInterface = ApiInterface.create().getBrandData("en")

        apiInterface.enqueue(object : Callback<JsonMainCreated> {
            override fun onResponse(call: Call<JsonMainCreated>?, response: Response<JsonMainCreated>?) {
                Log.d(TAG, "Success")
                if (response?.body() != null) {
                    val data = response.body()?.result?.data?.items?.toMutableList()!!
                    recyclerAdapter.setListItemsForRecyclerAdapter(data)
                }
                Log.d(TAG, "Success but null?! " + response?.body().toString())
            }

            override fun onFailure(call: Call<JsonMainCreated>?, t: Throwable?) {
                Log.d(TAG, "Failure " + t.toString())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.menu_search)
        val searchView = item?.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean = false

            override fun onQueryTextChange(newText: String): Boolean {
                recyclerAdapter.filterDataBySearch(newText.trim())
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}