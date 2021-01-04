package com.base.library

import android.app.Application
import com.base.library.rxRetrofit.RxRetrofitApp

object BaseApp {
    lateinit var application: Application

    fun init(application: Application) {
        this.application = application
        RxRetrofitApp.application = application
    }
}