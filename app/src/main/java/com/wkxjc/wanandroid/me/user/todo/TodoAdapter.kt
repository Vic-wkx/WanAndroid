package com.wkxjc.wanandroid.me.user.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.BaseViewHolder
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.common.bean.Todos
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val todos: Todos = Todos()) : RecyclerView.Adapter<BaseViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false)
        return BaseViewHolder(view)
    }

    override fun getItemCount() = todos.datas.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.tvTodoTitle.text = todos.datas[position].title
    }

    fun refresh(todos:Todos){
        this.todos.refresh(todos)
        notifyDataSetChanged()
    }
}