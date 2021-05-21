package com.wkxjc.wanandroid.common.user

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.base.library.BaseApp
import com.base.library.project.showToast
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.HttpManager
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.common.bean.ArticleBean
import com.wkxjc.wanandroid.me.login.LoginDialog

class Tourist : User {
    override val logonButtonDisplayedResId: Int = R.string.login
    override val isLogon = false
    override val name = BaseApp.application.getString(R.string.tourist)
    override val avatar: String? = null
    override val avatarFallbackResId = R.drawable.ic_avatar_tourist

    override fun loginOn(name: String) {
        SPUtils.getInstance(LOGIN_INFO).put(IS_LOGIN, true)
        SPUtils.getInstance(LOGIN_INFO).put(USER_NAME, name)
    }

    override fun onClickCollect(httpManager: HttpManager, bean: ArticleBean, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, position: Int) {
        showToast(R.string.please_login_first)
    }

    override fun onClickLogon(fragment: Fragment) {
        LoginDialog().show(fragment.childFragmentManager, LoginDialog::class.java.simpleName)
    }

    override fun onClickMyCollection(context: Context) {
        showToast(R.string.please_login_first)
    }

    override fun onClickMyTODO(context: Context) {
        showToast(R.string.please_login_first)
    }

}