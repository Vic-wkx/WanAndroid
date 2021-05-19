package com.wkxjc.wanandroid.me.common.api

import com.alibaba.fastjson.JSON
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.api.BaseApi
import com.wkxjc.wanandroid.home.common.api.ApiService
import com.wkxjc.wanandroid.me.LOGIN_INFO
import com.wkxjc.wanandroid.me.common.bean.Todos
import io.reactivex.Observable
import okhttp3.Headers

/**
 * Description:
 * 页码从1开始，拼接在url 上
 * status 状态， 1-完成；0未完成; 默认全部展示；
 * type 创建时传入的类型, 默认全部展示
 * priority 创建时传入的优先级；默认全部展示
 * orderby 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；
 *
 * @author  Alpinist Wang
 * Date:    2021/5/19
 */
class TodoApi : BaseApi() {
    var page = 1
    var status: Int = 0
    var type: Int? = null
    var priority: Int? = null
    var orderBy: Int? = null

    override fun getObservable(): Observable<String> {
        apiConfig.headers = Headers.headersOf(COOKIE_HEADER_KEY, SPUtils.getInstance(LOGIN_INFO).getString(COOKIE))
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getTodoList(page, status, type, priority, orderBy)
    }

    fun convert(result: String): Todos {
        return JSON.parseObject(result, Todos::class.javaObjectType)
    }

    fun resetPage() {
        page = 1
    }

    fun nextPage() {
        page++
    }
}