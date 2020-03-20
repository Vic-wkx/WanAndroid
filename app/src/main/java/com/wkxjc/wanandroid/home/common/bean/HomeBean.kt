package com.wkxjc.wanandroid.home.common.bean

data class HomeBean(
    val banners: MutableList<BannerBean> = mutableListOf(),
    val articles: MutableList<String> = mutableListOf("hahaha")
)