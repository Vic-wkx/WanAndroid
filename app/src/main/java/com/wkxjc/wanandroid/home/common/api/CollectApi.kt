package com.wkxjc.wanandroid.home.common.api

import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.me.common.api.COOKIE
import com.wkxjc.wanandroid.me.common.api.COOKIE_HEADER_KEY
import com.wkxjc.wanandroid.me.common.api.LOGIN_INFO
import io.reactivex.Observable
import okhttp3.Headers

class CollectApi(var articleId: Int) : BaseApi() {

    override fun getObservable(): Observable<String> {
        apiConfig.headers = Headers.headersOf(COOKIE_HEADER_KEY, SPUtils.getInstance(LOGIN_INFO).getString(COOKIE))
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.collectArticleInside(articleId)
    }

}