package com.example.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.constants.AppConstants
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.model.MealDetail
import com.example.myapplication.model.MealResponse
import com.example.myapplication.service.MealAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.example.myapplication.model.MealResponse as MealResponse1

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val BASE_URL = "https://www.themealdb.com/"

    private var mealDetail: MealDetail? = null
    private var clickedFoods: String? = null
    private var compositeDisposable: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        clickedFoods = intent.getStringExtra(AppConstants.SELECTED_DETAILS_NAME)

        compositeDisposable = CompositeDisposable()


        if (!clickedFoods.isNullOrEmpty()) {
            getAllDetails(clickedFoods!!)
        }


    }


    private fun getAllDetails(clickedFoods: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(MealAPI::class.java)

        compositeDisposable?.add(
            retrofit.getDetails(this.clickedFoods ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse2)
        )


    }

    private fun handleResponse2(response3: MealDetail) {

        val mealDetail = response3
        mealDetail.meals?.let { meals ->
            if (meals.isNotEmpty()) {
                val meal = meals[0]
                binding.titleTextView.text=meal.strMeal
                binding.instructionsTextView.text = meal.strInstructions
                binding.categoryTextView.text=meal.strCategory
                binding.areaTextView.text=meal.strArea
                val imageUrl = meal.strMealThumb

                Glide.with(this)
                    .load(imageUrl)
                    .into(binding.DetailsImage)
            }
            }
        }


    }

































