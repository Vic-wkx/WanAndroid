package com.wkxjc.wanandroid.me

import android.util.Log
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.common.api.TodoApi
import com.wkxjc.wanandroid.me.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity

class MeFragment : BaseFragment() {

    private val httpManager = HttpManager()
    private val todoApi = TodoApi()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            Log.d("~~~", "result: $result")
        }

        override fun onError(error: Throwable) {
            Log.d("~~~", "error:$error")
        }
    }

    override fun layoutId() = R.layout.fragment_me

    override fun initView() {
        tvLogin.setOnClickListener {
            startActivity<LoginActivity>()
        }
        btnTodo.setOnClickListener {
            httpManager.request(todoApi, listener)
        }
    }

    override fun initData() {
    }

}