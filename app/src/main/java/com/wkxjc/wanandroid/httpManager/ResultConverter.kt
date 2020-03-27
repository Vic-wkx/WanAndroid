package com.wkxjc.wanandroid.httpManager

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.converter.IResultConverter

open class ResultConverter : IResultConverter {
    override fun convert(response: String): String {
        val result = JSON.parseObject(response, BaseResult::class.javaObjectType)
        if (result.errorCode != 0) {
            throw IllegalArgumentException("errorCode: ${result.errorCode}, errorMessage: ${result.errorMsg}")
        }
        return result.data ?: ""
    }
}