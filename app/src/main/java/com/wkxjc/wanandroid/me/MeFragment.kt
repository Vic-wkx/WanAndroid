package com.wkxjc.wanandroid.me

import com.base.library.project.BaseFragment
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.collection.CollectionActivity
import com.wkxjc.wanandroid.me.common.api.LOGIN_INFO
import com.wkxjc.wanandroid.me.login.LoginActivity
import com.wkxjc.wanandroid.me.todo.TodoActivity
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_me

    override fun initView() {
        tvLogin.setOnClickListener {
            myStartActivity<LoginActivity>()
        }
        btnMyTodo.setOnClickListener {
            myStartActivity<TodoActivity>()
        }
        btnMyCollection.setOnClickListener {
            myStartActivity<CollectionActivity>()
        }
        btnExitLogin.setOnClickListener {
            SPUtils.getInstance(LOGIN_INFO).clear()
        }
    }

    override fun initData() {
    }
}