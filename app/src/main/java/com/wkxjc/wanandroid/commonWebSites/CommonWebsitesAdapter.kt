package com.wkxjc.wanandroid.commonWebSites

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.myStartActivity
import com.wkxjc.wanandroid.common.artical.LINK
import com.wkxjc.wanandroid.common.artical.WebActivity
import com.wkxjc.wanandroid.databinding.ItemWebsiteBinding
import com.wkxjc.wanandroid.home.common.bean.CommonWebsites


class CommonWebsitesAdapter(private val websites: CommonWebsites = CommonWebsites()) : RecyclerView.Adapter<CommonWebsitesAdapter.CommonWebsitesViewHolder>() {
    private lateinit var context: Context

    inner class CommonWebsitesViewHolder(val binding: ItemWebsiteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonWebsitesAdapter.CommonWebsitesViewHolder {
        context = parent.context
        val binding = ItemWebsiteBinding.inflate(LayoutInflater.from(context), parent, false)
        return CommonWebsitesViewHolder(binding)
    }

    override fun getItemCount() = websites.data.size

    override fun onBindViewHolder(holder: CommonWebsitesAdapter.CommonWebsitesViewHolder, position: Int) {
        val bean = websites.data[position]
        holder.binding.tvCommonWebsite.text = bean.name
        holder.binding.root.setOnClickListener {
            context.myStartActivity<WebActivity>(LINK to bean.link)
        }
    }

    fun refresh(websites: CommonWebsites) {
        this.websites.refresh(websites)
        notifyDataSetChanged()
    }

}