package com.wkxjc.wanandroid.httpManager

import com.base.library.rxRetrofit.common.header.IHttpResponseProcessor
import okhttp3.Request
import okhttp3.Response

open class HttpResponseProcessor : IHttpResponseProcessor {
    override fun handleResponse(request: Request, response: Response): Response {
        // 在这里可以处理http返回的错误码：response.code()，这里的错误码不同于BaseResult中的errorCode
        if (response.code >= 400) throw Throwable("Http response code = ${response.code}, request: ${request.url}")
        return response
    }
}