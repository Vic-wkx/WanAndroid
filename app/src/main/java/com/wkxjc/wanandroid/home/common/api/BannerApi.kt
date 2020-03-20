package com.wkxjc.wanandroid.home.common.api

import com.alibaba.fastjson.JSONArray
import com.base.library.rxRetrofit.http.api.BaseApi
import com.base.library.rxRetrofit.http.cache.CacheConfig
import com.wkxjc.wanandroid.home.common.bean.BannerBean
import io.reactivex.Observable

class BannerApi : BaseApi() {

    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getBanner()
    }

    fun convert(result: HashMap<BaseApi, Any>): List<BannerBean> {
        return JSONArray.parseArray(result[this] as String, BannerBean::class.java)
    }

}