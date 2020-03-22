package com.wkxjc.wanandroid.home.common.bean

data class KnowledgeTreeBean(
    val children: MutableList<Children> = mutableListOf(),
    val courseId: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val order: Int = 0,
    val parentChapterId: Int = 0,
    val userControlSetTop: Boolean = false,
    val visible: Int = 0
) {
    fun refresh(knowledgeTree: KnowledgeTreeBean) {
        children.clear()
        children.addAll(knowledgeTree.children)
    }
}