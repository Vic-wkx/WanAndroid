package com.wkxjc.wanandroid.httpManager

import android.util.Log
import com.base.library.rxRetrofit.common.header.DefaultHttpResponseProcessor
import com.base.library.rxRetrofit.common.utils.SPUtils
import okhttp3.Cookie
import okhttp3.Request
import okhttp3.Response

class HttpResponseProcessor : DefaultHttpResponseProcessor() {
    override fun handleResponse(request: Request, response: Response): Response {
        // 获取头部的 Cookie
        val cookies: List<Cookie> = Cookie.parseAll(request.url, response.headers)
        if (request.url.toString().contains("user/login") && cookies.isNotEmpty()) {
            // 去掉 List 转 String 的中括号
            Log.d("~~~", cookies.toString().substring(1, cookies.toString().length - 2))
            SPUtils.getInstance().put("Cookie", cookies.toString().substring(1, cookies.toString().length - 2))
        }
        return super.handleResponse(request, response)
    }
}