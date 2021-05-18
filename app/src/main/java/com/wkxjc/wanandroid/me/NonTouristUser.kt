package com.wkxjc.wanandroid.me

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.base.library.BaseApp
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.HttpManager
import com.wkxjc.wanandroid.MyApplication
import com.wkxjc.wanandroid.home.common.api.CollectApi
import com.wkxjc.wanandroid.home.common.bean.ArticleBean
import com.wkxjc.wanandroid.me.common.api.HomePageCancelCollectionApi
import com.wkxjc.wanandroid.me.user.todo.TodoActivity

class NonTouristUser : User {
    override val isLogon = true
    override val avatar: String = ""
    override val name: String = SPUtils.getInstance(LOGIN_INFO).getString(USER_NAME)
    override val logonButtonDisplayedResId: Int = com.wkxjc.wanandroid.R.string.login_out
    override fun loginOut() {
        SPUtils.getInstance(LOGIN_INFO).clear()
        MyApplication.user = Tourist()
    }

    override fun onClickCollect(httpManager: HttpManager, bean: ArticleBean, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, position: Int) {
        httpManager.request(if (bean.collect) HomePageCancelCollectionApi(bean.id) else CollectApi(bean.id))
        // Directly display succeed UI, maybe it's not a normal process, but I think it's more user-friendly
        bean.collect = !bean.collect
        adapter.notifyItemChanged(position)
    }

    override fun onClickLogon(context: Context) {
        loginOut()
    }

    override fun onClickMyCollection(context: Context) {
        context.myStartActivity<TodoActivity>()
    }

    override fun onClickMyTODO(context: Context) {
        context.myStartActivity<TodoActivity>()
    }
}