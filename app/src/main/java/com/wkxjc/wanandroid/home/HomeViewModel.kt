package com.wkxjc.wanandroid.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wkxjc.wanandroid.home.common.bean.Articles
import com.wkxjc.wanandroid.home.common.bean.Banners
import com.wkxjc.wanandroid.home.common.bean.HomeBean

class HomeViewModel : ViewModel() {
    val homeBean: MutableLiveData<HomeBean> = MutableLiveData(HomeBean())
    var isRefreshing: MutableLiveData<Boolean> = MutableLiveData(true)

    fun refresh(banners: Banners, articles: Articles) {
        homeBean.value?.refresh(banners, articles)
    }

}