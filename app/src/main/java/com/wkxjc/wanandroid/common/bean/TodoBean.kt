package com.wkxjc.wanandroid.common.bean

data class TodoBean(
    val completeDateStr: String? = null,
    val content: String? = null,
    val date: Long? = null,
    val dateStr: String? = null,
    val id: Int? = null,
    val priority: Int? = null,
    val status: Int,
    var title: String,
    val type: Int? = null,
    val userId: Int? = null
) {
    fun isCompleted(): Boolean {
        return status == 1
    }
}