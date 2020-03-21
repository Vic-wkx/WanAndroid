package com.wkxjc.wanandroid.home.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.bean.PublicAccountBean
import io.reactivex.Observable

class PublicAccountApi : BaseApi() {
    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getPublicAccount()
    }

    fun convert(result: String): List<PublicAccountBean> {
        return JSON.parseArray(result, PublicAccountBean::class.javaObjectType)
    }

}