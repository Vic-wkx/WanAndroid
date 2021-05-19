package com.wkxjc.wanandroid.me.common.bean

data class Todos(
    val curPage: Int = 0,
    val datas: MutableList<TodoBean> = mutableListOf(),
    val offset: Int = 0,
    val over: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0
) {
    fun refresh(todos: Todos) {
        datas.clear()
        datas.addAll(todos.datas)
    }

    fun loadMore(todos: Todos) {
        datas.addAll(todos.datas)
    }
}