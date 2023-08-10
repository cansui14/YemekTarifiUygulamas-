package com.example.myapplication.model

data class MealResponse(
    val meals: ArrayList<MealEntity>
)

data class MealEntity(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)