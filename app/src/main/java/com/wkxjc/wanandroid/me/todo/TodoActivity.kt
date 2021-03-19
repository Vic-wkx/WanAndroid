package com.wkxjc.wanandroid.me.todo

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.databinding.ActivityTodoBinding
import com.wkxjc.wanandroid.me.common.api.TodoApi

class TodoActivity : BaseActivity() {
    private val binding by lazy { ActivityTodoBinding.inflate(layoutInflater) }

    override fun createBinding() = binding.root
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

    override fun initView() {
        binding.rvTodo.layoutManager = LinearLayoutManager(this)
        binding.rvTodo.adapter = todoAdapter
    }

    override fun initData() {
        httpManager.request(todoApi, listener)
    }
}
