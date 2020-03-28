package com.wkxjc.wanandroid.me.collection

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.BaseViewHolder
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.artical.LINK
import com.wkxjc.wanandroid.artical.WebActivity
import com.wkxjc.wanandroid.home.common.bean.Collections
import kotlinx.android.synthetic.main.item_collection.view.*
import org.jetbrains.anko.startActivity

class CollectionAdapter(private val collections: Collections = Collections()) : RecyclerView.Adapter<BaseViewHolder>() {
    private lateinit var context: Context
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
            context.startActivity<WebActivity>(LINK to bean.link)
        }
    }

    fun refresh(collections: Collections){
        this.collections.refresh(collections)
        notifyDataSetChanged()
    }

}