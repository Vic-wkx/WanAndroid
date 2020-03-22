package com.wkxjc.wanandroid.home.common.bean

data class KnowledgeTrees(val data: MutableList<KnowledgeTreeBean> = mutableListOf()) {
    fun refresh(knowledgeTrees: KnowledgeTrees) {
        data.clear()
        data.addAll(knowledgeTrees.data)
    }
}