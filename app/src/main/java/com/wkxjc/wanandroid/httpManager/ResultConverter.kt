package com.wkxjc.wanandroid.httpManager

import android.util.Log
import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.http.converter.IResultConverter

open class ResultConverter : IResultConverter {
    override fun convert(response: String): String {
        val result = JSON.parseObject(response, BaseResult::class.javaObjectType)
        if (result.errorCode != 0) {
            Log.d("~~~", "errorCode: ${result.errorCode}, errorMessage: ${result.errorMsg}")
        }
        return result.data
    }
}