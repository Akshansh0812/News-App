package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.newsapp.R
import com.example.newsapp.database.ArticleDatabase
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.viewModels.NewsViewModel
import com.example.newsapp.viewModels.NewsViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    val viewModel : NewsViewModel by lazy{
        val articleDatabase = ArticleDatabase.getInstance(this)
        val newsRepository = NewsRepository(articleDatabase)
        val newsViewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        ViewModelProvider(this,newsViewModelProviderFactory)[NewsViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = Navigation.findNavController(this,R.id.newsNavHostFragment)

        NavigationUI.setupWithNavController(bottomNavigation,navController)
    }
}