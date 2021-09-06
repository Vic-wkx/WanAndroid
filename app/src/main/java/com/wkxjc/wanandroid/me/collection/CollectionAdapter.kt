package com.wkxjc.wanandroid.me.collection

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.databinding.ItemCollectionBinding
import com.wkxjc.wanandroid.databinding.ItemLoadMoreBinding
import com.wkxjc.wanandroid.common.bean.CollectionBean
import com.wkxjc.wanandroid.common.bean.Collections
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


const val ARTICLE = 0x1
const val LOAD_MORE = 0x2
const val HEADER_COUNT = 0
const val FOOTER_COUNT = 1
const val HEADER_FOOTER_COUNT = HEADER_COUNT + FOOTER_COUNT

// preload more articles for better user experience
const val PRELOAD = 10

class CollectionAdapter(private val collections: Collections = Collections()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoadingMore = false
    lateinit var loadMore: () -> Unit
    lateinit var onItemClickListener: (View, CollectionBean, Int) -> Unit
    private lateinit var context: Context
    private var noMore = false

    inner class ArticleViewHolder(val binding: ItemCollectionBinding) : RecyclerView.ViewHolder(binding.root)
    inner class LoadMoreViewHolder(val binding: ItemLoadMoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            LOAD_MORE -> LoadMoreViewHolder(ItemLoadMoreBinding.inflate(LayoutInflater.from(context), parent, false))
            else -> ArticleViewHolder(ItemCollectionBinding.inflate(LayoutInflater.from(context), parent, false))
        }
    }

    override fun getItemCount() = collections.datas.size + HEADER_FOOTER_COUNT

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArticleViewHolder -> {
                val bean = collections.datas[position]
                holder.binding.tvTitle.text = Html.fromHtml(bean.title, Html.FROM_HTML_MODE_LEGACY)
                holder.binding.tvTime.text = String.format(
                    context.getString(R.string.time_is), DateTimeFormatter.ofPattern("yyyy-MM-dd").format(
                        ZonedDateTime.ofInstant(
                            Date(bean.publishTime).toInstant(),
                            ZoneId.systemDefault()
                        )
                    )
                )
                holder.binding.root.setOnClickListener {
                    onItemClickListener.invoke(it, bean, holder.adapterPosition)
                }
                holder.binding.ivCancelCollect.setOnClickListener {
                    onItemClickListener.invoke(it, bean, holder.adapterPosition)
                }
                if (position >= collections.datas.size - 1 - PRELOAD && !isLoadingMore && !noMore) {
                    isLoadingMore = true
                    loadMore.invoke()
                }
            }
            is LoadMoreViewHolder -> {
                holder.binding.tvLoadMore.setText(if (noMore) R.string.no_more_data else R.string.load_more)
                holder.binding.root.visibility = if (collections.datas.isEmpty()) View.GONE else View.VISIBLE
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount - 1 -> LOAD_MORE
            else -> ARTICLE
        }
    }

    fun refresh(collections: Collections) {
        isLoadingMore = false
        noMore = false
        this.collections.refresh(collections)
        notifyDataSetChanged()
    }

    fun loadMore(collections: Collections) {
        if (collections.datas.isNullOrEmpty()) {
            noMore = true
            notifyItemChanged(itemCount - 1)
        } else {
            this.collections.loadMore(collections)
            notifyDataSetChanged()
        }
        isLoadingMore = false
    }

    fun removeItem(position: Int) {
        collections.datas.removeAt(position)
        notifyItemRemoved(position)
    }

    fun isEmpty() = collections.datas.isEmpty()
}