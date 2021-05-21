package com.wkxjc.wanandroid.common.api

import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.common.user.LOGIN_INFO
import io.reactivex.Observable
import okhttp3.Headers

class AddTodoApi : BaseApi() {
    var title: String = ""
    override fun getObservable(): Observable<String> {
        apiConfig.headers = Headers.headersOf(COOKIE_HEADER_KEY, SPUtils.getInstance(LOGIN_INFO).getString(COOKIE))
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.addTodo(title)
    }
}