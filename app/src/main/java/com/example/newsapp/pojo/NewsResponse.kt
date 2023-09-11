package com.example.newsapp.pojo

import com.example.newsapp.pojo.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)