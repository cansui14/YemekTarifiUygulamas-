package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.CategoryAdapter
import com.example.myapplication.constants.AppConstants
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Category
import com.example.myapplication.model.CategoryResponse
import com.example.myapplication.service.MealAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CategoryAdapter.Listener {
    private lateinit var binding: ActivityMainBinding


    private val BASE_URL = "https://www.themealdb.com/"

    private var categories: CategoryResponse? = null
    private var recyclerViewAdapter: CategoryAdapter? = null

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        compositeDisposable = CompositeDisposable()


        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadData()
    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(MealAPI::class.java)


        compositeDisposable?.add(
            retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )


    }

    private fun handleResponse(response: CategoryResponse) {
        categories = response

        categories?.categories?.let { list->
            recyclerViewAdapter = CategoryAdapter(list, this@MainActivity)
            binding.recyclerView.adapter = recyclerViewAdapter


        }
    }

    override fun onItemClick(category: Category) {
        Toast.makeText(this, "Clicked : ${category.strCategory}", Toast.LENGTH_LONG).show()

        val intent = Intent(this,MealActivity::class.java)
        intent.putExtra(AppConstants.SELECTED_CATEGORY_NAME,category.strCategory)

        startActivity(intent)
    }


}