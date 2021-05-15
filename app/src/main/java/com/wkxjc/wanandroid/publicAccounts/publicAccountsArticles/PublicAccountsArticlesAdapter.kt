package com.wkxjc.wanandroid.publicAccounts.publicAccountsArticles

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.getActualBindingClass
import com.base.library.project.myStartActivity
import com.wkxjc.wanandroid.common.artical.LINK
import com.wkxjc.wanandroid.common.artical.WebActivity
import com.wkxjc.wanandroid.databinding.ItemPublicAccountsArticleBinding
import com.wkxjc.wanandroid.home.common.bean.Articles


class PublicAccountsArticlesAdapter(private val articles: Articles = Articles()) : RecyclerView.Adapter<PublicAccountsArticlesAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(val binding: ItemPublicAccountsArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        Log.d("~~~", "actual: ${getActualBindingClass(javaClass)}")
        val binding = ItemPublicAccountsArticleBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = articles.datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = articles.datas[position]
        holder.binding.tvTitle.text = bean.title
        holder.binding.root.setOnClickListener {
            context.myStartActivity<WebActivity>(LINK to bean.link)
        }
    }

    fun refresh(articles: Articles) {
        this.articles.refresh(articles)
        notifyDataSetChanged()
    }

}