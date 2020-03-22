package com.wkxjc.wanandroid.home.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.bean.KnowledgeTreeBean
import com.wkxjc.wanandroid.home.common.bean.KnowledgeTrees
import io.reactivex.Observable

class KnowledgeTreeApi : BaseApi() {
    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getKnowledgeTree()
    }

    fun convert(result: String): KnowledgeTrees {
        return KnowledgeTrees(JSON.parseArray(result, KnowledgeTreeBean::class.javaObjectType))
    }
}