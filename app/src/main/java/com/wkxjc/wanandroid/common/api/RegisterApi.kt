package com.wkxjc.wanandroid.common.api

import com.base.library.rxRetrofit.http.api.BaseApi
import io.reactivex.Observable

class RegisterApi : BaseApi() {

    var userName = ""
    var password = ""
    var confirmPassword = ""

    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.register(userName, password, confirmPassword)
    }
}