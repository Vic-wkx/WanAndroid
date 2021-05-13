package com.wkxjc.wanandroid.common.httpManager

import android.util.Log
import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.converter.IResultConverter

open class ResultConverter : IResultConverter {
    override fun convert(response: String): String {
        Log.d("~~~", "response:$response")
        val result = JSON.parseObject(response, BaseResult::class.javaObjectType)
        if (result.errorCode != 0) {
            throw IllegalArgumentException("errorCode: ${result.errorCode}, errorMessage: ${result.errorMsg}")
        }
        Log.d("~~~", "result:${result.data}")
        return result.data ?: ""
    }
}