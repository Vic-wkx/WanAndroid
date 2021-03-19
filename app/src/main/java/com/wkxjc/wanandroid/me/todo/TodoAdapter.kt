package com.wkxjc.wanandroid.me.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wkxjc.wanandroid.databinding.ItemTodoBinding
import com.wkxjc.wanandroid.me.common.bean.Todos


class TodoAdapter(private val todos: Todos = Todos()) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = todos.datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTodoTitle.text = todos.datas[position].title
    }

    fun refresh(todos: Todos) {
        this.todos.refresh(todos)
        notifyDataSetChanged()
    }
}