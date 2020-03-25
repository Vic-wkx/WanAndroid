package com.wkxjc.wanandroid.me.common.api

import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.api.ApiService
import io.reactivex.Observable

class LoginApi : BaseApi() {
    var username = ""
    var password = ""

    init {
        apiConfig.httpResponseProcessor = LoginHttpResponseProcessor()
    }

    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.login(username, password)
    }
}