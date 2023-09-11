package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.pojo.Article
import com.google.android.material.imageview.ShapeableImageView

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private lateinit var onItemClick:((Article)->Unit)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holder.image)
            holder.sources.text = article.source.name
            holder.title.text = article.title
            holder.description.text = article.description
            holder.published.text = article.publishedAt
            holder.itemView.setOnClickListener{
                onItemClick.invoke(article)
            }
        }
    }


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image : ShapeableImageView
        val sources : TextView
        val title : TextView
        val description : TextView
        val published : TextView


        init {
            image = itemView.findViewById(R.id.ivArticleImage)
            sources = itemView.findViewById(R.id.tvSource)
            title = itemView.findViewById(R.id.tvTitle)
            description = itemView.findViewById(R.id.tvDescription)
            published = itemView.findViewById(R.id.tvPublishedAt)
        }
    }
}









