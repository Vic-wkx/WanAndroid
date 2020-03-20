package com.wkxjc.wanandroid.httpManager

import android.util.Log
import com.alibaba.fastjson.JSONObject
import com.base.library.rxRetrofit.http.converter.DefaultResultConverter

class ResultConverter : DefaultResultConverter() {
    override fun convert(response: String): String {
        val result = JSONObject.parseObject(response, BaseResult::class.java)
        if (result.errorCode != 0) {
            Log.d("~~~", "errorCode: ${result.errorCode}, errorMessage: ${result.errorMsg}")
        }
        return result.data
    }
}