package com.wkxjc.wanandroid.home.common.api

import com.base.library.rxRetrofit.http.api.BaseApi
import com.google.gson.Gson
import com.wkxjc.wanandroid.home.common.bean.Articles
import io.reactivex.Observable
import java.util.*

class ArticleApi : BaseApi() {
    var page = 0
    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getHomeArticles(page)
    }

    fun nextPage() {
        page++
    }

    fun convert(resultMap: HashMap<BaseApi, Any>): Articles {
        return Gson().fromJson(resultMap[this] as String, Articles::class.javaObjectType)
    }
}