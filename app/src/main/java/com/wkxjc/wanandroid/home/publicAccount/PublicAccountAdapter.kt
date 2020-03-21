package com.wkxjc.wanandroid.home.publicAccount

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.bean.PublicAccountBean
import com.wkxjc.wanandroid.home.common.bean.PublicAccounts
import com.wkxjc.wanandroid.home.publicAccount.PublicAccountArticles.PublicAccountArticlesActivity
import kotlinx.android.synthetic.main.item_public_account.view.*
import org.jetbrains.anko.startActivity

class PublicAccountAdapter(private val publicAccounts: PublicAccounts = PublicAccounts()) : RecyclerView.Adapter<PublicAccountAdapter.PublicAccountViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicAccountViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_public_account, parent, false)
        return PublicAccountViewHolder(view)
    }

    override fun getItemCount() = publicAccounts.data.size

    override fun onBindViewHolder(holder: PublicAccountViewHolder, position: Int) {
        val bean = publicAccounts.data[position]
        holder.itemView.tvPublicAccountName.text = bean.name
        holder.itemView.setOnClickListener {
            context.startActivity<PublicAccountArticlesActivity>("id" to bean.id)
        }
    }

    fun refresh(data: List<PublicAccountBean>) {
        publicAccounts.refresh(data)
        notifyDataSetChanged()
    }

    class PublicAccountViewHolder(view: View) : RecyclerView.ViewHolder(view)

}