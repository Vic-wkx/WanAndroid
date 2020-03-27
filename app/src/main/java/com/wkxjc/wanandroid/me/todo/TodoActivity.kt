package com.wkxjc.wanandroid.me.todo

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.common.api.TodoApi
import kotlinx.android.synthetic.main.activity_todo.*

class TodoActivity : BaseActivity() {
    private val httpManager = HttpManager()
    private val todoApi = TodoApi()
    private val todoAdapter = TodoAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            todoAdapter.refresh(todoApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }
    }

    override fun layoutId() = R.layout.activity_todo

    override fun initView() {
        rvTodo.layoutManager = LinearLayoutManager(this)
        rvTodo.adapter = todoAdapter
    }

    override fun initData() {
        httpManager.request(todoApi, listener)
    }
}
