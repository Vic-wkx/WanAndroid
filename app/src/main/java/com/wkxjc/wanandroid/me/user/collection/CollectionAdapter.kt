package com.wkxjc.wanandroid.me.user.collection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wkxjc.wanandroid.databinding.ItemCollectionBinding
import com.wkxjc.wanandroid.home.common.bean.CollectionBean
import com.wkxjc.wanandroid.home.common.bean.Collections


class CollectionAdapter(private val collections: Collections = Collections()) : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {
    private lateinit var context: Context
    lateinit var onItemClickListener: OnItemClickListener

    inner class ViewHolder(val binding: ItemCollectionBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(view: View, bean: CollectionBean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ItemCollectionBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = collections.datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = collections.datas[position]
        holder.binding.tvCollectionTitle.text = bean.title
        holder.binding.root.setOnClickListener {
            onItemClickListener.onItemClick(it, bean)
        }
        holder.binding.tvCancelCollect.setOnClickListener {
            collections.remove(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            onItemClickListener.onItemClick(it, bean)
        }
    }

    fun refresh(collections: Collections) {
        this.collections.refresh(collections)
        notifyDataSetChanged()
    }

}