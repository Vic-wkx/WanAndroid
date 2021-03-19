package com.wkxjc.wanandroid.common.httpManager

import com.base.library.rxRetrofit.http.httpList.DefaultHttpListConfig

class MyHttpListApiConfig : DefaultHttpListConfig() {
    override var showLoading = false
}