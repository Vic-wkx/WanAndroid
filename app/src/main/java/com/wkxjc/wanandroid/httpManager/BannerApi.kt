package com.wkxjc.wanandroid.httpManager

import com.base.library.rxRetrofit.http.api.BaseApi
import io.reactivex.Observable

class BannerApi : BaseApi() {

    init {
        showLoading = false
    }

    override fun getObservable(): Observable<String> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getBanner()
    }

}