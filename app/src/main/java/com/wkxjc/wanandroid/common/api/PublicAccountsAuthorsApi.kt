package com.wkxjc.wanandroid.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.common.bean.PublicAccountsAuthorBean
import io.reactivex.Observable

class PublicAccountsAuthorsApi : BaseApi() {
    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getPublicAccount()
    }

    fun convert(result: String): List<PublicAccountsAuthorBean> {
        return JSON.parseArray(result, PublicAccountsAuthorBean::class.javaObjectType)
    }
}