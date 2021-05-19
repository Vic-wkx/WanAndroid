package com.wkxjc.wanandroid.me

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.base.library.rxRetrofit.http.HttpManager
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.api.CollectApi
import com.wkxjc.wanandroid.home.common.bean.ArticleBean
import com.wkxjc.wanandroid.me.common.api.HomePageCancelCollectionApi
import com.wkxjc.wanandroid.me.user.todo.TodoActivity

class NonTourist : User {
    override val isLogon = true
    override val name: String = SPUtils.getInstance(LOGIN_INFO).getString(USER_NAME)
    override val avatar: String? = null
    override val avatarFallbackResId = R.drawable.ic_avatar_non_tourist
    override val logonButtonDisplayedResId: Int = com.wkxjc.wanandroid.R.string.login_out
    override fun loginOut() {
        SPUtils.getInstance(LOGIN_INFO).clear()
    }

    override fun onClickCollect(httpManager: HttpManager, bean: ArticleBean, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, position: Int) {
        httpManager.request(if (bean.collect) HomePageCancelCollectionApi(bean.id) else CollectApi(bean.id))
        // Directly display succeed UI, maybe it's not a normal process, but I think it's more user-friendly
        bean.collect = !bean.collect
        adapter.notifyItemChanged(position)
    }

    override fun onClickLogon(fragment: Fragment) {
        ConfirmLoginOutDialog().show(fragment.childFragmentManager, ConfirmLoginOutDialog::class.java.simpleName)
    }

    override fun onClickMyCollection(context: Context) {
        context.myStartActivity<TodoActivity>()
    }

    override fun onClickMyTODO(context: Context) {
        context.myStartActivity<TodoActivity>()
    }
}