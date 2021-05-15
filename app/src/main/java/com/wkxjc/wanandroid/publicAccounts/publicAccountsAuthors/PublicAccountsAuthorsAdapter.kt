package com.wkxjc.wanandroid.publicAccounts.publicAccountsAuthors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wkxjc.wanandroid.databinding.ItemPublicAccountsAuthorBinding
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthorBean
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthors


class PublicAccountsAuthorsAdapter(private val publicAccountsAuthors: PublicAccountsAuthors = PublicAccountsAuthors()) : RecyclerView.Adapter<PublicAccountsAuthorsAdapter.PublicAccountViewHolder>() {

    inner class PublicAccountViewHolder(val binding: ItemPublicAccountsAuthorBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context
    lateinit var onItemClick: (PublicAccountsAuthorBean) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicAccountViewHolder {
        context = parent.context
        val binding = ItemPublicAccountsAuthorBinding.inflate(LayoutInflater.from(context), parent, false)
        return PublicAccountViewHolder(binding)
    }

    override fun getItemCount() = publicAccountsAuthors.data.size

    override fun onBindViewHolder(holder: PublicAccountViewHolder, position: Int) {
        val bean = publicAccountsAuthors.data[position]
        holder.binding.tvPublicAccountName.text = bean.name

        holder.binding.root.setOnClickListener {
            onItemClick.invoke(bean)
        }
    }

    fun refresh(data: List<PublicAccountsAuthorBean>) {
        publicAccountsAuthors.refresh(data)
        notifyDataSetChanged()
    }


}