package com.wkxjc.wanandroid.me.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.api.ApiService
import com.wkxjc.wanandroid.me.common.bean.LoginBean
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

    fun convert(result: String): LoginBean {
        return JSON.parseObject(result, LoginBean::class.javaObjectType)
    }
}