package com.wkxjc.wanandroid

import android.app.Application
import com.base.library.rxRetrofit.RxRetrofitApp
import com.wkxjc.wanandroid.httpManager.ResultConverter
import com.wkxjc.wanandroid.httpManager.ApiConfig

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

