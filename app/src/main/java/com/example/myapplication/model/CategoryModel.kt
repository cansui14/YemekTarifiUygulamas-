package com.example.myapplication.model

data class CategoryResponse(
    val categories: ArrayList<Category>
)


data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)