package com.wkxjc.wanandroid.home

import android.util.Log
import com.base.library.rxRetrofit.http.listener.HttpListener

class BannerListener : HttpListener() {
    override fun onNext(result: String) {
        Log.d("~~~", "result:$result")
    }

    override fun onError(error: Throwable) {
        Log.d("~~~", "error:$error")
    }
}