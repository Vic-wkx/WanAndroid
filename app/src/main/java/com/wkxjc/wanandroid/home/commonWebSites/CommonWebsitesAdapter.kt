package com.wkxjc.wanandroid.home.commonWebSites

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.BaseViewHolder
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.artical.WebActivity
import com.wkxjc.wanandroid.home.common.bean.CommonWebsites
import kotlinx.android.synthetic.main.item_website.view.*
import org.jetbrains.anko.startActivity

class CommonWebsitesAdapter(private val websites: CommonWebsites = CommonWebsites()) : RecyclerView.Adapter<BaseViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_website, parent, false)
        return BaseViewHolder(view)
    }

    override fun getItemCount() = websites.data.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val bean = websites.data[position]
        holder.itemView.tvCommonWebsite.text = bean.name
        holder.itemView.setOnClickListener {
            context.startActivity<WebActivity>("link" to bean.link)
        }
    }

    fun refresh(websites: CommonWebsites) {
        this.websites.refresh(websites)
        notifyDataSetChanged()
    }

}