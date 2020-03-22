package com.wkxjc.wanandroid.httpManager

import com.base.library.rxRetrofit.http.api.DefaultApiConfig
import com.base.library.rxRetrofit.http.cache.CacheConfig

class ApiConfig : DefaultApiConfig() {
    override var baseUrl = "https://www.wanandroid.com"

    override var cacheConfig = CacheConfig(true)
}