package com.wkxjc.wanandroid

import android.app.Application
import android.content.Context
import android.os.LocaleList
import com.base.library.BaseApp
import com.base.library.rxRetrofit.RxRetrofitApp
import com.wkxjc.wanandroid.common.httpManager.MyHttpApiConfig
import com.wkxjc.wanandroid.common.httpManager.MyHttpListApiConfig
import com.base.library.project.utils.LanguageUtils

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        BaseApp.init(this)
        RxRetrofitApp.apiConfig = MyHttpApiConfig()
        RxRetrofitApp.httpListConfig = MyHttpListApiConfig()
    }
}

