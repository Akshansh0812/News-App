package com.example.newsapp.pojo

import com.example.newsapp.pojo.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)