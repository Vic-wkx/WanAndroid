package com.wkxjc.wanandroid.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.banner.ImageAdapter
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.artical.ArticleActivity
import com.wkxjc.wanandroid.home.common.bean.Articles
import com.wkxjc.wanandroid.home.common.bean.Banners
import com.wkxjc.wanandroid.home.common.bean.HomeBean
import com.wkxjc.wanandroid.home.publicAccount.PublicAccountActivity
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_banner.view.*
import kotlinx.android.synthetic.main.item_shortcut.view.*
import org.jetbrains.anko.startActivity

const val BANNER = 1
const val SHORTCUT = 2
const val ARTICLE = 0
const val LOAD_MORE = -1
const val HEADER_COUNT = 2
const val FOOTER_COUNT = 1
const val HEADER_FOOTER_COUNT = HEADER_COUNT + FOOTER_COUNT

class HomeAdapter(private val homeBean: HomeBean = HomeBean()) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        context = parent.context
        return HomeViewHolder(LayoutInflater.from(context).inflate(getLayoutIdByViewType(viewType), parent, false))
    }

    override fun getItemCount() = homeBean.articles.datas.size + HEADER_FOOTER_COUNT

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when (getViewTypeByPosition(position)) {
            BANNER -> {
                val banner: Banner<String, ImageAdapter> = holder.itemView.banner as Banner<String, ImageAdapter>
                banner.setAdapter(ImageAdapter(homeBean.banners.data.map { it.imagePath }))
            }
            SHORTCUT -> {
                holder.itemView.tvPublicAccount.setOnClickListener {
                    context.startActivity<PublicAccountActivity>()
                }
            }
            ARTICLE -> {
                val bean = homeBean.articles.datas[position - HEADER_COUNT]
                holder.itemView.tvTitle.text = bean.title
                holder.itemView.setOnClickListener {
                    context.startActivity<ArticleActivity>("link" to bean.link)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getViewTypeByPosition(position)
    }

    private fun getViewTypeByPosition(position: Int): Int {
        return when (position) {
            0 -> BANNER
            1 -> SHORTCUT
            itemCount - 1 -> LOAD_MORE
            else -> ARTICLE
        }
    }

    private fun getLayoutIdByViewType(viewType: Int): Int {
        return when (viewType) {
            BANNER -> R.layout.item_banner
            SHORTCUT -> R.layout.item_shortcut
            LOAD_MORE -> R.layout.item_load_more
            else -> R.layout.item_article
        }
    }

    fun refresh(banners: Banners, articles: Articles) {
        homeBean.refresh(banners, articles)
        notifyDataSetChanged()
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view)

}