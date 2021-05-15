package com.wkxjc.wanandroid.publicAccounts.publicAccountsAuthors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wkxjc.wanandroid.databinding.ItemPublicAccountsAuthorBinding
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthorBean
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthors


class PublicAccountsAuthorsAdapter(private val publicAccountsAuthors: PublicAccountsAuthors = PublicAccountsAuthors()) : RecyclerView.Adapter<PublicAccountsAuthorsAdapter.PublicAccountViewHolder>() {

    lateinit var onItemClick: (PublicAccountsAuthorBean) -> Unit

    inner class PublicAccountViewHolder(val binding: ItemPublicAccountsAuthorBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context
    private lateinit var selectedItem: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicAccountViewHolder {
        context = parent.context
        val binding = ItemPublicAccountsAuthorBinding.inflate(LayoutInflater.from(context), parent, false)
        return PublicAccountViewHolder(binding)
    }

    override fun getItemCount() = publicAccountsAuthors.data.size

    override fun onBindViewHolder(holder: PublicAccountViewHolder, position: Int) {
        initSelectedItem(position, holder.binding.root)
        val bean = publicAccountsAuthors.data[position]
        holder.binding.tvPublicAccountName.text = bean.name
        holder.binding.root.setOnClickListener {
            if (it == selectedItem) return@setOnClickListener
            updateSelectedItem(it)
            onItemClick.invoke(bean)
        }
    }

    private fun initSelectedItem(position: Int, view: View) {
        if (position == 0 && !::selectedItem.isInitialized) {
            selectedItem = view
            view.isSelected = true
        }
    }

    private fun updateSelectedItem(it: View) {
        selectedItem.isSelected = false
        selectedItem = it
        it.isSelected = true
    }

    fun refresh(data: List<PublicAccountsAuthorBean>) {
        publicAccountsAuthors.refresh(data)
        notifyDataSetChanged()
    }
}