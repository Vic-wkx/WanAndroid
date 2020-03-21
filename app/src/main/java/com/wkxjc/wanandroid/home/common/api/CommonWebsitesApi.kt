package com.wkxjc.wanandroid.home.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.bean.CommonWebsiteBean
import com.wkxjc.wanandroid.home.common.bean.CommonWebsites
import io.reactivex.Observable

class CommonWebsitesApi : BaseApi() {

    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getCommonWebsites()
    }

    fun convert(result: String): CommonWebsites {
        return CommonWebsites(JSON.parseArray(result, CommonWebsiteBean::class.javaObjectType))
    }
}