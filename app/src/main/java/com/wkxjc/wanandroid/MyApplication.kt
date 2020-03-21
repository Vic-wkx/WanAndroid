package com.wkxjc.wanandroid

import android.app.Application
import com.base.library.rxRetrofit.RxRetrofitApp
import com.wkxjc.wanandroid.httpManager.ApiConfig
import com.wkxjc.wanandroid.httpManager.ResultConverter

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RxRetrofitApp.apply {
            application = this@MyApplication
            apiConfig = ApiConfig()
            resultConverter = ResultConverter()
        }
    }
}

