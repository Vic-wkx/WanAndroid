package com.wkxjc.wanandroid.me.common.api

import android.util.Log
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.api.ApiService
import io.reactivex.Observable
import okhttp3.Headers

class TodoApi : BaseApi() {
    var page = 0
    var status = 0
    var type = 0
    var priority = 0
    var orderBy = 0

    init {
        headers = Headers.headersOf("Cookie", SPUtils.getInstance().getString("Cookie"))
        Log.d("~~~","")
    }

    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getTodoList(page, status, type, priority, orderBy)
    }
}