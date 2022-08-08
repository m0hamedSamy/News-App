package com.example.onlinepractice.homePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinepractice.R
import com.example.onlinepractice.network.Article

class ArticleAdapter(var articles: List<Article>?, val onArticleListener: OnArticleListener) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {



    class ArticleViewHolder(view: View, onArticleListener: OnArticleListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val tvTitle: TextView
        val tvDesc: TextView
        val ivImage: ImageView
        val onArticleListener: OnArticleListener

        init {
            tvTitle = view.findViewById(R.id.tv_articleTitle)
            tvDesc = view.findViewById(R.id.tv_articleDescription)
            ivImage = view.findViewById(R.id.iv_articleImg)
            this.onArticleListener = onArticleListener

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onArticleListener.onArticleClicked(adapterPosition)
        }
    }

    interface OnArticleListener{
        fun onArticleClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_layout, parent, false)

        return ArticleViewHolder(view, onArticleListener)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles?.get(position)
        holder.tvTitle.text = article?.title
        holder.tvDesc.text = article?.description

        Glide
            .with(holder.itemView)
            .load(article?.urlToImage)
            .into(holder.ivImage)
    }

    override fun getItemCount() = articles?.size ?: 0

}