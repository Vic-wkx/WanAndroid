package com.wkxjc.wanandroid.me.collection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.BaseViewHolder
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.bean.CollectionBean
import com.wkxjc.wanandroid.home.common.bean.Collections
import kotlinx.android.synthetic.main.item_collection.view.*

class CollectionAdapter(private val collections: Collections = Collections()) : RecyclerView.Adapter<BaseViewHolder>() {
    private lateinit var context: Context
    lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, bean: CollectionBean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_collection, parent, false)
        return BaseViewHolder(view)
    }

    override fun getItemCount() = collections.datas.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val bean = collections.datas[position]
        holder.itemView.tvCollectionTitle.text = bean.title
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(it, bean)
        }
        holder.itemView.tvCancelCollect.setOnClickListener {
            onItemClickListener.onItemClick(it, bean)
        }
    }

    fun refresh(collections: Collections) {
        this.collections.refresh(collections)
        notifyDataSetChanged()
    }

}