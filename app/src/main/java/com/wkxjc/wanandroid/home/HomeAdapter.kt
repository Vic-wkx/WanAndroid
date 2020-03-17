package com.wkxjc.wanandroid.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wkxjc.wanandroid.R
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_banner.view.*

const val BANNER = 1
const val ARTICLE = 0
const val LOAD_MORE = -1

class HomeAdapter(private val data: List<String>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        context = parent.context
        return HomeViewHolder(
            LayoutInflater.from(context).inflate(
                getLayoutIdByViewType(viewType), parent, false
            )
        )
    }

    override fun getItemCount() = data.size + 2

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when (getViewTypeByPosition(position)) {
            BANNER -> {
                Log.d("~~~", "banner")
                val banner: Banner<String, ImageAdapter> =
                    holder.itemView.banner as Banner<String, ImageAdapter>
                banner.setAdapter(
                    ImageAdapter(
                        listOf(
                            "https://www.baidu.com/img/bd_logo1.png",
                            "https://csdnimg.cn/cdn/content-toolbar/csdn-logo_.png"
                        )
                    )
                )
            }
            ARTICLE -> {
                holder.itemView.tvTitle.text = "haha"
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

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view)

}