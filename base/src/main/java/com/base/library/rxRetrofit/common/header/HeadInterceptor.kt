package com.base.library.rxRetrofit.common.header

import android.util.Log
import com.base.library.rxRetrofit.RxRetrofitApp
import com.base.library.rxRetrofit.http.api.BaseApi
import okhttp3.Cookie
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Description:
 * Http请求head拦截器
 *
 * @author  WZG
 * Date:    2019-05-04
 */
class HeadInterceptor(private val api: BaseApi?, private val headers: Headers?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        // 添加api中的header信息
        if (headers != null) requestBuilder.headers(headers)
        val request = requestBuilder.method(original.method, original.body)
            .build()
        val response = chain.proceed(request)
        // 获取头部的Cookie,注意：可以通过Cooke.parseAll()来获取
        val cookies: List<Cookie> = Cookie.parseAll(request.url, response.headers)
        Log.d("~~~", cookies.toString())
        return if (RxRetrofitApp.apiConfig.ignoreResponseProcessor || api?.ignoreResponseProcessor == true) response
        else RxRetrofitApp.httpResponseProcessor.handleResponse(response)
    }
}
