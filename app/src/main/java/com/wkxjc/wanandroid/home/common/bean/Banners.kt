package com.wkxjc.wanandroid.home.common.bean

data class Banners(val data: MutableList<BannerBean> = mutableListOf()) {

    fun refresh(banners: Banners) {
        this.data.clear()
        this.data.addAll(banners.data)
    }
}