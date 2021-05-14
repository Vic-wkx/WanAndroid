package com.wkxjc.wanandroid.publicAccounts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wkxjc.wanandroid.databinding.ItemPublicAccountBinding
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthorBean
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthors


class PublicAccountsAuthorsAdapter(private val publicAccountsAuthors: PublicAccountsAuthors = PublicAccountsAuthors()) : RecyclerView.Adapter<PublicAccountsAuthorsAdapter.PublicAccountViewHolder>() {

    inner class PublicAccountViewHolder(val binding: ItemPublicAccountBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicAccountsAuthorsAdapter.PublicAccountViewHolder {
        context = parent.context
        val binding = ItemPublicAccountBinding.inflate(LayoutInflater.from(context), parent, false)
        return PublicAccountViewHolder(binding)
    }

    override fun getItemCount() = publicAccountsAuthors.data.size

    override fun onBindViewHolder(holder: PublicAccountViewHolder, position: Int) {
        val bean = publicAccountsAuthors.data[position]
        holder.binding.tvPublicAccountName.text = bean.name

//        holder.binding.root.setOnClickListener {
//            context.myStartActivity<PublicAccountsArticlesActivity>("id" to bean.id)
//        }
    }

    fun refresh(data: List<PublicAccountsAuthorBean>) {
        publicAccountsAuthors.refresh(data)
        notifyDataSetChanged()
    }


}