package com.base.library.project

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {
    lateinit var context: Context
//    lateinit var onItemClickListener: (view: View, bean: ArticleBean) -> Unit
    lateinit var loadMore: () -> Unit
    var isLoadingMore = false

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
//
//    }
}