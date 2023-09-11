package com.example.newsapp.database

import android.content.Context
import androidx.room.*
import com.example.newsapp.pojo.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converter::class)

abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile
        var INSTANCE:ArticleDatabase? = null

        @Synchronized
        fun getInstance(context : Context):ArticleDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_db.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as ArticleDatabase
        }
    }
}

