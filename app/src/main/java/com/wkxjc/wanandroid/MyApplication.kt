package com.wkxjc.wanandroid

import android.app.Application
import com.base.library.BaseApp
import com.base.library.rxRetrofit.RxRetrofitApp
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.wkxjc.wanandroid.common.httpManager.MyHttpApiConfig
import com.wkxjc.wanandroid.common.httpManager.MyHttpListApiConfig
import com.wkxjc.wanandroid.me.*

class MyApplication : Application() {
    companion object {
        lateinit var user: User
    }

    override fun onCreate() {
        super.onCreate()
        BaseApp.init(this)
        user = if (SPUtils.getInstance(LOGIN_INFO).getBoolean(IS_LOGIN)) NonTouristUser() else Tourist()
        RxRetrofitApp.apiConfig = MyHttpApiConfig()
        RxRetrofitApp.httpListConfig = MyHttpListApiConfig()
    }
}

