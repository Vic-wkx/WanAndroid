package com.wkxjc.wanandroid.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.BaseViewHolder
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.common.artical.LINK
import com.wkxjc.wanandroid.common.artical.WebActivity
import com.wkxjc.wanandroid.common.banner.ImageAdapter
import com.wkxjc.wanandroid.home.common.bean.*
import com.wkxjc.wanandroid.home.commonWebSites.CommonWebsitesActivity
import com.wkxjc.wanandroid.home.knowledge.KnowledgeTreeActivity
import com.wkxjc.wanandroid.home.navigation.NavigationActivity
import com.wkxjc.wanandroid.home.publicAccounts.PublicAccountActivity
import com.youth.banner.listener.OnBannerListener
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

class HomeAdapter(private val homeBean: HomeBean = HomeBean()) : RecyclerView.Adapter<BaseViewHolder>() {
    private lateinit var context: Context
    lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, bean: ArticleBean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        context = parent.context
        return BaseViewHolder(LayoutInflater.from(context).inflate(getLayoutIdByViewType(viewType), parent, false))
    }

    override fun getItemCount() = homeBean.articles.datas.size + HEADER_FOOTER_COUNT

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (getViewTypeByPosition(position)) {
            BANNER -> {
                holder.itemView.banner.adapter = ImageAdapter(homeBean.banners.data)
                holder.itemView.banner.setOnBannerListener(object : OnBannerListener<BannerBean> {
                    override fun onBannerChanged(position: Int) {
                    }

                    override fun OnBannerClick(data: BannerBean, position: Int) {
                        context.startActivity<WebActivity>(LINK to data.url)
                    }
                })
            }
            SHORTCUT -> {
                holder.itemView.tvPublicAccounts.setOnClickListener {
                    context.startActivity<PublicAccountActivity>()
                }
                holder.itemView.tvCommonWebsites.setOnClickListener {
                    context.startActivity<CommonWebsitesActivity>()
                }
                holder.itemView.tvKnowledgeTree.setOnClickListener {
                    context.startActivity<KnowledgeTreeActivity>()
                }
                holder.itemView.tvNavigation.setOnClickListener {
                    context.startActivity<NavigationActivity>()
                }
            }
            ARTICLE -> {
                val bean = homeBean.articles.datas[position - HEADER_COUNT]
                holder.itemView.tvTitle.text = bean.title
                holder.itemView.setOnClickListener {
                    onItemClickListener.onItemClick(it, bean)
                }
                holder.itemView.tvCollect.setOnClickListener {
                    onItemClickListener.onItemClick(it, bean)
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

}