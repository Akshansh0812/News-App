package com.example.newsapp.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemPreviewBinding
import com.example.newsapp.pojo.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {



    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ItemPreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(article.urlToImage)
                .into(holder.binding.ivArticleImage)

            holder.binding.tvSource.text = article.source.name
            holder.binding.tvTitle.text = article.title
            holder.binding.tvDescription.text = article.description
            holder.binding.tvPublishedAt.text = article.publishedAt

            setOnClickListener{
                onItemClickListener?.let{it(article)}
            }
        }
    }
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


    inner class ArticleViewHolder(val binding: ItemPreviewBinding) : RecyclerView.ViewHolder(binding.root){


//        val image : ImageView
//        val sources : TextView
//        val title : TextView
//        val description : TextView
//        val published : TextView
//
//
//        init {
//            image = itemView.findViewById(R.id.ivArticleImage)
//            sources = itemView.findViewById(R.id.tvSource)
//            title = itemView.findViewById(R.id.tvTitle)
//            description = itemView.findViewById(R.id.tvDescription)
//            published = itemView.findViewById(R.id.tvPublishedAt)
//        }
    }
}









