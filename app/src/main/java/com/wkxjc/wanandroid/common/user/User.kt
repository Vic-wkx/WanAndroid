package com.wkxjc.wanandroid.common.user

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.HttpManager
import com.wkxjc.wanandroid.common.bean.ArticleBean

const val LOGIN_INFO = "login_info"
const val IS_LOGIN = "IS_LOGIN"
const val USER_NAME = "USER_NAME"
const val DESCRIPTION = "DESCRIPTION"

interface User {
    val logonButtonDisplayedResId: Int
    val isLogon: Boolean
    val name: String
    val avatar: String?
    val avatarFallbackResId: Int
    val description: String
        get() = SPUtils.getInstance(LOGIN_INFO).getString(DESCRIPTION).takeUnless { it.isNullOrBlank() } ?: "诶，就是玩儿～"

    fun loginOn(name: String) {
    }

    fun loginOut() {
    }

    fun onClickCollect(httpManager: HttpManager, bean: ArticleBean, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, position: Int)

    fun onClickLogon(fragment: Fragment)

    fun onClickMyCollection(context: Context)

    fun onClickMyTODO(context: Context)
}