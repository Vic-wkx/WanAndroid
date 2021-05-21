package com.wkxjc.wanandroid.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.common.bean.Articles
import io.reactivex.Observable

class PublicAccountsArticlesApi : BaseApi() {
    var id: Int = 0
    var page = 0

    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getPublicAccountArticles(id, page)
    }

    fun convert(result: String): Articles {
        return JSON.parseObject(result, Articles::class.javaObjectType)
    }
}