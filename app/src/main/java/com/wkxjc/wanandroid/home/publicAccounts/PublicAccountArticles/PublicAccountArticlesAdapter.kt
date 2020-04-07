package com.wkxjc.wanandroid.home.publicAccounts.PublicAccountArticles

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.BaseViewHolder
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.common.artical.LINK
import com.wkxjc.wanandroid.common.artical.WebActivity
import com.wkxjc.wanandroid.home.common.bean.Articles
import kotlinx.android.synthetic.main.item_public_account_article.view.*
import org.jetbrains.anko.startActivity

class PublicAccountArticlesAdapter(private val articles: Articles = Articles()) : RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_public_account_article, parent, false)
        return BaseViewHolder(view)
    }

    override fun getItemCount() = articles.datas.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val bean = articles.datas[position]
        holder.itemView.tvPublicAccountArticleTitle.text = bean.title
        holder.itemView.setOnClickListener {
            context.startActivity<WebActivity>(LINK to bean.link)
        }
    }

    fun refresh(articles: Articles) {
        this.articles.refresh(articles)
        notifyDataSetChanged()
    }

}