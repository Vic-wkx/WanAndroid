package com.wkxjc.wanandroid.httpManager

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    // 微信公众号列表接口
    @GET("wxarticle/chapters/json")
    fun getWeChatPublicAccount(): Observable<String>
}