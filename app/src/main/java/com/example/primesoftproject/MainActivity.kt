package com.example.primesoftproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.primesoftproject.data.BrandItemViewModel
import com.example.primesoftproject.fragments.add.AddFragment
import com.example.primesoftproject.model.Item
import com.example.primesoftproject.model.JsonMainCreated
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var data: MutableList<Item>
    lateinit var changedData: MutableList<Item>
    lateinit var bottomNav:BottomNavigationView
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        val apiInterface = ApiInterface.create().getBrandData("en")

        apiInterface.enqueue( object : Callback<JsonMainCreated> {
            override fun onResponse(call: Call<JsonMainCreated>?, response: Response<JsonMainCreated>?) {
                Log.d("TAGG", "Success")
                if(response?.body() != null)
                    data = response.body()?.result?.data?.items as MutableList<Item>
                    recyclerAdapter.setListItemsForRecyclerAdapter(data)
                Log.d("TAGG", "Success but null?! "+response?.body().toString())
            }

            override fun onFailure(call: Call<JsonMainCreated>?, t: Throwable?) {
                Log.d("TAGG", "Failure "+t.toString())
            }
        })

/*        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                 //R.id.menu_list_button-> {onBackPressed()}
                R.id.menu_brand_button->{val intentBrand = Intent(this@MainActivity,BrandsActivity::class.java)
                    startActivity(intentBrand) }
            }
            return@setOnNavigationItemSelectedListener true
        }*/
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val item = menu?.findItem(R.id.menu_search)
        val searchView = item?.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                changedData = data
                data.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    changedData.forEach {
                        if (it.name.toLowerCase(Locale.getDefault()).contains(searchText)){
                            data.add(it)
                            recyclerAdapter.setListItemsForRecyclerAdapter(data)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    data.clear()
                    data.addAll(changedData)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}