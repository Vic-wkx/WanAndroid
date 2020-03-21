package com.wkxjc.wanandroid.home.publicAccount.PublicAccountArticles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.artical.ArticleActivity
import com.wkxjc.wanandroid.home.common.bean.Articles
import kotlinx.android.synthetic.main.item_public_account_article.view.*
import org.jetbrains.anko.startActivity

class PublicAccountArticlesAdapter(private val articles: Articles = Articles()) : RecyclerView.Adapter<PublicAccountArticlesAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_public_account_article, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = articles.datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = articles.datas[position]
        holder.itemView.tvPublicAccountArticleTitle.text = bean.title
        holder.itemView.setOnClickListener {
            context.startActivity<ArticleActivity>("link" to bean.link)
        }
    }

    fun refresh(articles: Articles) {
        this.articles.refresh(articles)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}