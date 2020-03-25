package com.wkxjc.wanandroid.httpManager

import com.base.library.rxRetrofit.common.header.IHttpResponseProcessor
import com.base.library.rxRetrofit.http.api.ApiConfig
import com.base.library.rxRetrofit.http.converter.IResultConverter

class MyApiConfig : ApiConfig() {
    override var baseUrl = "https://www.wanandroid.com"
    override var resultConverter: IResultConverter = ResultConverter()
    override var httpResponseProcessor: IHttpResponseProcessor = HttpResponseProcessor()
}