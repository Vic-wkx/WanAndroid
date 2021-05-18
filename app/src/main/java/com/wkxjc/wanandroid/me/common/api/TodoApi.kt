package com.wkxjc.wanandroid.me.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.api.ApiService
import com.wkxjc.wanandroid.me.common.bean.Todos
import io.reactivex.Observable
import okhttp3.Headers
import com.wkxjc.wanandroid.me.LOGIN_INFO

class TodoApi : BaseApi() {
    var page = 0
    var status = 0
    var type = 0
    var priority = 0
    var orderBy = 0

    override fun getObservable(): Observable<String> {
        apiConfig.headers = Headers.headersOf(COOKIE_HEADER_KEY, SPUtils.getInstance(LOGIN_INFO).getString(COOKIE))
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getTodoList(page, status, type, priority, orderBy)
    }

    fun convert(result: String): Todos {
        return JSON.parseObject(result, Todos::class.javaObjectType)
    }
}