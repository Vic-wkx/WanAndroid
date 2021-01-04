package com.wkxjc.wanandroid.home.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.bean.Articles
import io.reactivex.Observable
import java.util.*

class ArticleApi : BaseApi() {
    var page = 0
    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getHomeArticles(page)
    }

    fun resetPage() {
        page = 0
    }

    fun nextPage() {
        page++
    }

    fun convert(resultMap: HashMap<BaseApi, Any>): Articles {
        return JSON.parseObject(resultMap[this] as String, Articles::class.javaObjectType)
    }

    fun convert(result: String): Articles {
        return JSON.parseObject(result, Articles::class.javaObjectType)
    }

}