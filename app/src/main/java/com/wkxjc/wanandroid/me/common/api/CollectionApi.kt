package com.wkxjc.wanandroid.me.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.api.ApiService
import com.wkxjc.wanandroid.home.common.bean.Collections
import com.wkxjc.wanandroid.me.LOGIN_INFO
import io.reactivex.Observable
import okhttp3.Headers

class CollectionApi : BaseApi() {
    var page = 0

    init {
        apiConfig.cacheConfig.cache = false
    }

    override fun getObservable(): Observable<String> {
        apiConfig.headers = Headers.headersOf(COOKIE_HEADER_KEY, SPUtils.getInstance(LOGIN_INFO).getString(COOKIE))
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getCollectArticles(page)
    }

    fun convert(result: String): Collections {
        return JSON.parseObject(result, Collections::class.javaObjectType)
    }
}