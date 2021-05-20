package com.wkxjc.wanandroid.me.common.bean

data class TodoBean(
    val completeDateStr: String,
    val content: String,
    val date: Long,
    val dateStr: String,
    val id: Int,
    val priority: Int,
    val status: Int,
    val title: String,
    val type: Int,
    val userId: Int
) {
    fun isCompleted(): Boolean {
        return type == 1
    }
}