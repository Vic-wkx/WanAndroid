package com.wkxjc.wanandroid.me

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.HttpManager
import com.wkxjc.wanandroid.home.common.bean.ArticleBean

const val LOGIN_INFO = "login_info"
const val IS_LOGIN = "IS_LOGIN"
const val USER_NAME = "USER_NAME"
const val AVATAR = "AVATAR"
const val DESCRIPTION = "DESCRIPTION"
const val PASSWORD = "PASSWORD"

interface User {
    val logonButtonDisplayedResId: Int
    val isLogon: Boolean
    val name: String
    val avatar: String
    val description: String
        get() = SPUtils.getInstance(LOGIN_INFO).getString(DESCRIPTION)

    fun loginOn(name: String) {
    }

    fun loginOut() {
    }

    fun onClickCollect(httpManager: HttpManager, bean: ArticleBean, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, position: Int)

    fun onClickLogon(context: Context)

    fun onClickMyCollection(context: Context)

    fun onClickMyTODO(context: Context)
}