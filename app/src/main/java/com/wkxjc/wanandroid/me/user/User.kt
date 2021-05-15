package com.wkxjc.wanandroid.me.user

import com.base.library.rxRetrofit.common.utils.SPUtils
import com.wkxjc.wanandroid.me.common.api.IS_LOGIN
import com.wkxjc.wanandroid.me.common.api.LOGIN_INFO
import com.wkxjc.wanandroid.me.common.api.USER_NAME

object User {
    val isLogon: Boolean
        // get newest value in every use
        get() = SPUtils.getInstance(LOGIN_INFO).getBoolean(IS_LOGIN)
    val name: String
        get() = SPUtils.getInstance(LOGIN_INFO).getString(USER_NAME)

    fun loginOn(name: String) {
        SPUtils.getInstance(LOGIN_INFO).put(IS_LOGIN, true)
        SPUtils.getInstance(LOGIN_INFO).put(USER_NAME, name)
    }

    fun loginOut() {
        SPUtils.getInstance(LOGIN_INFO).clear()
    }
}