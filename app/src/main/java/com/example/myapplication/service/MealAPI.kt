package com.example.myapplication.service

import com.example.myapplication.model.CategoryResponse
import com.example.myapplication.model.MealDetail
import com.example.myapplication.model.MealResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.collections.List
import kotlin.collections.List as List1

interface MealAPI {




    @GET("api/json/v1/1/categories.php")
    fun getData(): Observable<CategoryResponse>

    @GET("api/json/v1/1/filter.php")
    fun getMeals(@Query(value = "c",encoded = true) clickedCategoryName:String): Observable<MealResponse>

    @GET("api/json/v1/1/lookup.php")
    fun getDetails(@Query(value = "i",encoded = true) clickedFoods: String): Observable<MealDetail>
//(@Query(value = "i",encoded = true) clickedFoods: String)


}