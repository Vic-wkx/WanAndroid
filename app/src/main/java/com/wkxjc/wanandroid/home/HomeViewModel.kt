package com.wkxjc.wanandroid.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lewis.widget.ui.Status
import com.wkxjc.wanandroid.home.common.bean.HomeBean

class HomeViewModel : ViewModel() {
    val homeBean = MutableLiveData<HomeBean>()
    val status = MutableLiveData<Status>()
    var isRefreshing = MutableLiveData(true)
}