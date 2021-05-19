package com.wkxjc.wanandroid.home.common.bean

data class Collections(
    val curPage: Int = 0,
    val datas: MutableList<CollectionBean> = mutableListOf(),
    val offset: Int = 0,
    val over: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0
) {
    fun refresh(collections: Collections) {
        datas.clear()
        datas.addAll(collections.datas)
    }

    fun remove(position: Int) {
        datas.removeAt(position)
    }

    fun loadMore(collections: Collections) {
        datas.addAll(collections.datas)
    }
}