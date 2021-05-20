package com.wkxjc.wanandroid.me.user.todo

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.databinding.ActivityTodoBinding
import com.wkxjc.wanandroid.me.common.api.TodoApi
import com.wkxjc.wanandroid.me.common.bean.TodoBean


class TodoActivity : BaseActivity<ActivityTodoBinding>() {
    private val todoViewModel by viewModels<TodoViewModel>()
    private val statusView by lazy { StatusView.initInActivity(this) }
    private val httpManager = HttpManager()
    private val todoApi = TodoApi()
    private val todoAdapter = TodoAdapter()
    private var isSwitchingTodoType = false
    private val todoListener = object : HttpListener() {
        override fun onNext(result: String) {
            val todos = todoApi.convert(result)
            todoAdapter.refresh(todos)
            statusView.setStatus(if (todos.datas.isNotEmpty()) Status.NORMAL else Status.EMPTY)
            isSwitchingTodoType = false
        }

        override fun onError(error: Throwable) {
            statusView.setStatus(Status.ERROR)
        }
    }
    private val todoLoadMoreListener = object : HttpListener() {
        override fun onNext(result: String) {
            todoAdapter.loadMore(todoApi.convert(result))
        }

        override fun onError(error: Throwable) {
            statusView.setStatus(Status.ERROR)
        }

    }

    override fun initView() {
        binding.rvTodo.layoutManager = LinearLayoutManager(this)
        todoAdapter.loadMore = ::loadMore
        todoAdapter.onItemClickListener = ::onItemClick
        todoAdapter.onCheckChangedListener = ::onCheckedChanged
        binding.rvTodo.adapter = todoAdapter
        todoViewModel.todoStatus.observe(this) {
            todoApi.status = it
            initData()
        }
        statusView.setOnRetryBtnClickListener {
            initData()
        }
    }

    fun loadMore() {
        todoApi.nextPage()
        httpManager.request(todoApi, todoLoadMoreListener)
    }

    fun onItemClick(view: View, bean: TodoBean, position: Int) {
        when (view.id) {
            R.id.ivEdit -> {
                // Show EditTodoDialog, updateTodoApi
            }
            R.id.ivDelete -> {
                // DeleteTodoApi
            }
        }
    }

    fun onCheckedChanged(isChecked: Boolean, position: Int) {
        // first time, it will be called, please except this senario
//        todoAdapter.remove(position)
        // UpdateTodoCompletedStatusApi
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.todo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.switchTodoStatus -> {
                if (!isSwitchingTodoType) {
                    isSwitchingTodoType = true
                    if (todoViewModel.todoStatus.value == 0) {
                        item.setIcon(R.drawable.ic_todo)
                        todoViewModel.todoStatus.value = 1
                    } else {
                        item.setIcon(R.drawable.ic_completed_todo)
                        todoViewModel.todoStatus.value = 0
                    }
                }
            }
            R.id.newTodo -> {
                // Show EditTodoDialog, NewTodoApi
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initData() {
        todoApi.resetPage()
        statusView.setStatus(Status.LOADING)
        httpManager.request(todoApi, todoListener)
    }
}
