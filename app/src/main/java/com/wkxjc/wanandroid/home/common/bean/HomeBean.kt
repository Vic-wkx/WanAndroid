package com.wkxjc.wanandroid.home.common.bean

import android.util.Log

data class HomeBean(
    val banners: MutableList<BannerBean> = mutableListOf(),
    val articles: Articles = Articles(data = mutableListOf<ArticleBean>())
) {
    fun refresh(banners: List<BannerBean>, articles: Articles) {
        Log.d("~~~", "refresh:${articles.data.size}")
        this.banners.clear()
        this.banners.addAll(banners)
        this.articles.data.clear()
        this.articles.data.addAll(articles.data)
    }
}