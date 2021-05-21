package com.wkxjc.wanandroid.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.common.bean.Collections
import com.wkxjc.wanandroid.common.user.LOGIN_INFO
import io.reactivex.Observable
import okhttp3.Headers

class CollectionApi : BaseApi() {
    private var page = 0

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

    fun nextPage() {
        page++
    }
}