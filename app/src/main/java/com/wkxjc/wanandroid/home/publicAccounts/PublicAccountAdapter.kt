package com.wkxjc.wanandroid.home.publicAccounts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.myStartActivity
import com.wkxjc.wanandroid.databinding.ItemPublicAccountBinding
import com.wkxjc.wanandroid.home.common.bean.PublicAccountBean
import com.wkxjc.wanandroid.home.common.bean.PublicAccounts
import com.wkxjc.wanandroid.home.publicAccounts.PublicAccountArticles.PublicAccountArticlesActivity


class PublicAccountAdapter(private val publicAccounts: PublicAccounts = PublicAccounts()) : RecyclerView.Adapter<PublicAccountAdapter.PublicAccountViewHolder>() {

    inner class PublicAccountViewHolder(val binding: ItemPublicAccountBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicAccountAdapter.PublicAccountViewHolder {
        context = parent.context
        val binding = ItemPublicAccountBinding.inflate(LayoutInflater.from(context), parent, false)
        return PublicAccountViewHolder(binding)
    }

    override fun getItemCount() = publicAccounts.data.size

    override fun onBindViewHolder(holder: PublicAccountViewHolder, position: Int) {
        val bean = publicAccounts.data[position]
        holder.binding.tvPublicAccountName.text = bean.name
        holder.binding.root.setOnClickListener {
            context.myStartActivity<PublicAccountArticlesActivity>("id" to bean.id)
        }
    }

    fun refresh(data: List<PublicAccountBean>) {
        publicAccounts.refresh(data)
        notifyDataSetChanged()
    }


}