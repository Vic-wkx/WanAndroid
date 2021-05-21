package com.wkxjc.wanandroid.common.bean

data class CommonWebsites(val data: MutableList<CommonWebsiteBean> = mutableListOf()) {
    fun refresh(websites: CommonWebsites) {
        data.clear()
        data.addAll(websites.data)
    }
}