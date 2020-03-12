package com.wkxjc.wanandroid.httpManager

import com.base.library.rxRetrofit.http.api.DefaultApiConfig

class BaseApiConfig : DefaultApiConfig() {
    override var baseUrl = "https://www.wanandroid.com"
}