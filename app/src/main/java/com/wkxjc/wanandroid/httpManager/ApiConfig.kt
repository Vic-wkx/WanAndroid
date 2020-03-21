package com.wkxjc.wanandroid.httpManager

import com.base.library.rxRetrofit.http.api.DefaultApiConfig
import com.base.library.rxRetrofit.http.cache.CacheConfig
import com.wkxjc.wanandroid.BuildConfig

class ApiConfig : DefaultApiConfig() {
    override var baseUrl = "https://www.wanandroid.com"

    // DEBUG 时关闭缓存，保证每次都去服务器取最新数据
    override var cacheConfig = CacheConfig(!BuildConfig.DEBUG)
}