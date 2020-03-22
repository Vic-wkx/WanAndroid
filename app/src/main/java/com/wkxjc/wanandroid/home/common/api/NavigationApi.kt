package com.wkxjc.wanandroid.home.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.bean.NavigationBean
import com.wkxjc.wanandroid.home.common.bean.Navigations
import io.reactivex.Observable

class NavigationApi : BaseApi() {
    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getNavigation()
    }

    fun convert(result: String): Navigations {
        return Navigations(JSON.parseArray(result, NavigationBean::class.javaObjectType))
    }
}