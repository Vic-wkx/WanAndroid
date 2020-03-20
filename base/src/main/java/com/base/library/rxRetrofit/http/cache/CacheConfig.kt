package com.base.library.rxRetrofit.http.cache

import com.base.library.BuildConfig

/**
 * Description:
 * 网络请求缓存配置
 *
 * @author  Alpinist Wang
 * Date:    2019-04-25
 */
data class CacheConfig(
    // 是否需要缓存处理，默认 DEBUG 时关闭缓存，其他情况下开启缓存
    var cache: Boolean = !BuildConfig.DEBUG,
    // 有网的时候的缓存过期时间
    var onlineCacheTime: Int = 30,
    // 没网的时候的缓存过期时间
    var offlineCacheTime: Int = 60 * 60 * 24 * 30
)