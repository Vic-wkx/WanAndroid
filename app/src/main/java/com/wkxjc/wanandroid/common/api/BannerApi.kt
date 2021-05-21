package com.wkxjc.wanandroid.common.api

import com.alibaba.fastjson.JSONArray
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.common.bean.BannerBean
import com.wkxjc.wanandroid.common.bean.Banners
import io.reactivex.Observable

class BannerApi : BaseApi() {

    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getBanner()
    }

    fun convert(resultMap: HashMap<BaseApi, Any>): Banners {
        return Banners(JSONArray.parseArray(resultMap[this] as String, BannerBean::class.java))
    }
}