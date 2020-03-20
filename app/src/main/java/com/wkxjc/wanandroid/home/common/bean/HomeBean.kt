package com.wkxjc.wanandroid.home.common.bean

data class HomeBean(
    val banners: MutableList<BannerBean> = mutableListOf(),
    val articles: Articles = Articles()
) {
    fun refresh(banners: List<BannerBean>, articles: Articles) {
        this.banners.clear()
        this.banners.addAll(banners)
        this.articles.datas.clear()
        this.articles.datas.addAll(articles.datas)
    }
}