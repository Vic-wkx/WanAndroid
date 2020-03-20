package com.wkxjc.wanandroid.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.banner.ImageAdapter
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.bean.Articles
import com.wkxjc.wanandroid.home.common.bean.BannerBean
import com.wkxjc.wanandroid.home.common.bean.HomeBean
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_banner.view.*

const val BANNER = 1
const val ARTICLE = 0
const val LOAD_MORE = -1

class HomeAdapter(private val homeBean: HomeBean) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        context = parent.context
        return HomeViewHolder(LayoutInflater.from(context).inflate(getLayoutIdByViewType(viewType), parent, false))
    }

    override fun getItemCount() = homeBean.articles.datas.size + 2

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when (getViewTypeByPosition(position)) {
            BANNER -> {
                val banner: Banner<String, ImageAdapter> = holder.itemView.banner as Banner<String, ImageAdapter>
                banner.setAdapter(ImageAdapter(homeBean.banners.map { it.imagePath }))
            }
            ARTICLE -> {
                holder.itemView.tvTitle.text = homeBean.articles.datas[position - 1].title
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getViewTypeByPosition(position)
    }

    private fun getViewTypeByPosition(position: Int): Int {
        return when (position) {
            0 -> BANNER
            itemCount - 1 -> LOAD_MORE
            else -> ARTICLE
        }
    }

    private fun getLayoutIdByViewType(viewType: Int): Int {
        return when (viewType) {
            BANNER -> R.layout.item_banner
            LOAD_MORE -> R.layout.item_load_more
            else -> R.layout.item_article
        }
    }

    fun refresh(banners: List<BannerBean>, articles: Articles) {
        homeBean.refresh(banners, articles)
        notifyDataSetChanged()
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view)

}