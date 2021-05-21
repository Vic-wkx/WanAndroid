package com.wkxjc.wanandroid.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.common.bean.LoginBean
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