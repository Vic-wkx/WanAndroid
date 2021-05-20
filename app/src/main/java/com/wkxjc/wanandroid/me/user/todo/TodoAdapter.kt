package com.wkxjc.wanandroid.me.user.todo

import android.content.Context
import android.graphics.Paint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.databinding.ItemLoadMoreBinding
import com.wkxjc.wanandroid.databinding.ItemTodoBinding
import com.wkxjc.wanandroid.me.common.bean.TodoBean
import com.wkxjc.wanandroid.me.common.bean.Todos

const val TODO_ITEM = 0x1
const val LOAD_MORE = 0x2
const val HEADER_COUNT = 0
const val FOOTER_COUNT = 1
const val HEADER_FOOTER_COUNT = HEADER_COUNT + FOOTER_COUNT

// preload more articles for better user experience
const val PRE_LOAD = 10

class TodoAdapter(private val todos: Todos = Todos()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var onItemClickListener: (View, TodoBean, Int) -> Unit
    lateinit var onCheckChangedListener: (Boolean, TodoBean, Int) -> Unit
    lateinit var loadMore: () -> Unit
    var isLoadingMore = false
    private var noMore = false
    private lateinit var context: Context


    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)
    inner class LoadMoreViewHolder(val binding: ItemLoadMoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            LOAD_MORE -> LoadMoreViewHolder(ItemLoadMoreBinding.inflate(LayoutInflater.from(context), parent, false))
            else -> TodoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(context), parent, false))
        }
    }

    override fun getItemCount() = todos.datas.size + HEADER_FOOTER_COUNT

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TodoViewHolder -> {
                val bean = todos.datas[position]
                holder.binding.cbTodo.isChecked = bean.isCompleted()
                holder.binding.tvTodoTitle.apply {
                    // set/remove delete line
                    paintFlags = if (bean.isCompleted()) {
                        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                    text = Html.fromHtml(bean.title, Html.FROM_HTML_MODE_LEGACY)
                }
                holder.binding.ivEdit.visibility = if (bean.isCompleted()) View.GONE else View.VISIBLE
                holder.binding.ivEdit.setOnClickListener {
                    onItemClickListener.invoke(it, bean, holder.adapterPosition)
                }
                holder.binding.ivDelete.setOnClickListener {
                    onItemClickListener.invoke(it, bean, holder.adapterPosition)
                }
                holder.binding.cbTodo.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (buttonView.isPressed) {
                        onCheckChangedListener.invoke(isChecked, bean, holder.adapterPosition)
                    }
                }
                if (position - HEADER_COUNT >= todos.datas.size - 1 - PRE_LOAD && !isLoadingMore && !noMore) {
                    isLoadingMore = true
                    loadMore.invoke()
                }
            }
            is LoadMoreViewHolder -> {
                holder.binding.tvLoadMore.setText(if (noMore) R.string.no_more_data else R.string.load_more)
                holder.binding.root.visibility = if (todos.datas.isEmpty()) View.GONE else View.VISIBLE
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount - 1 -> LOAD_MORE
            else -> TODO_ITEM
        }
    }

    fun refresh(todos: Todos) {
        isLoadingMore = false
        noMore = false
        this.todos.refresh(todos)
        notifyDataSetChanged()
    }

    fun loadMore(todos: Todos) {
        if (todos.datas.isNullOrEmpty()) {
            noMore = true
            notifyItemChanged(itemCount - 1)
        } else {
            this.todos.loadMore(todos)
            notifyDataSetChanged()
        }
        isLoadingMore = false
    }

    fun remove(position: Int) {
        this.todos.datas.removeAt(position)
        notifyItemRemoved(position)
    }

    fun isEmpty(): Boolean {
        return todos.datas.isEmpty()
    }
}