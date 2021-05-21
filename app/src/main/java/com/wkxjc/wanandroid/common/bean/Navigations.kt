package com.wkxjc.wanandroid.common.bean

data class Navigations(val data: MutableList<NavigationBean> = mutableListOf()) {
    fun refresh(navigations: Navigations) {
        data.clear()
        data.addAll(navigations.data)
    }
}