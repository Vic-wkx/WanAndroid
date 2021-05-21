package com.wkxjc.wanandroid.me.todo

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.project.showToast
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.databinding.ActivityTodoBinding
import com.wkxjc.wanandroid.common.api.TodoApi
import com.wkxjc.wanandroid.common.api.UpdateTodoCompleteStatusApi
import com.wkxjc.wanandroid.common.bean.TodoBean


class TodoActivity : BaseActivity<ActivityTodoBinding>() {
    private val viewModel by viewModels<TodoViewModel>()
    private val statusView by lazy { StatusView.initInActivity(this) }
    private val httpManager = HttpManager()
    private val getTodoApi = TodoApi()
    private val todoAdapter = TodoAdapter()
    private var isSwitchingTodoType = false
    private val todoListener = object : HttpListener() {
        override fun onNext(result: String) {
            val todos = getTodoApi.convert(result)
            viewModel.todos.value = if (viewModel.todos.value == null) todos else viewModel.todos.value.apply {
                this?.refresh(todos)
            }
            isSwitchingTodoType = false
        }

        override fun onError(error: Throwable) {
            statusView.setStatus(Status.ERROR)
        }
    }
    private val todoLoadMoreListener = object : HttpListener() {
        override fun onNext(result: String) {
            todoAdapter.loadMore(getTodoApi.convert(result))
        }

        override fun onError(error: Throwable) {
            todoAdapter.isLoadingMore = false
            statusView.setStatus(Status.ERROR)
        }

    }

    override fun initView() {
        binding.rvTodo.layoutManager = LinearLayoutManager(this)
        todoAdapter.loadMore = ::loadMore
        todoAdapter.onItemClickListener = ::onItemClick
        todoAdapter.onCheckChangedListener = ::onCheckedChanged
        binding.rvTodo.adapter = todoAdapter
        viewModel.todoStatus.observe(this) {
            getTodoApi.status = it
            initData()
        }
        viewModel.todos.observe(this) {
            todoAdapter.refresh(it)
            statusView.setStatus(if (it.datas.isNotEmpty()) Status.NORMAL else Status.EMPTY)
        }
        viewModel.dataChanged.observe(this) {
            if (it) {
                viewModel.dataChanged.value = false
                initData()
            }
        }
        statusView.setOnRetryBtnClickListener {
            initData()
        }
    }

    fun loadMore() {
        getTodoApi.nextPage()
        httpManager.request(getTodoApi, todoLoadMoreListener)
    }

    fun onItemClick(view: View, bean: TodoBean, position: Int) {
        when (view.id) {
            R.id.ivEdit -> {
                // Show EditTodoDialog, UpdateTodoApi
                EditTodoDialog(bean).show(supportFragmentManager, EditTodoDialog::class.java.simpleName)
            }
            R.id.ivDelete -> {
                // DeleteTodoApi
                ConfirmDeleteTodoDialog(bean.id!!)
                    .show(supportFragmentManager, ConfirmDeleteTodoDialog::class.java.simpleName)
            }
        }
    }

    fun onCheckedChanged(isChecked: Boolean, bean: TodoBean, position: Int) {
        if (bean.isCompleted() && !isChecked) {
            httpManager.request(UpdateTodoCompleteStatusApi(bean.id!!, 0))
            todoAdapter.remove(position)
        } else if (!bean.isCompleted() && isChecked) {
            httpManager.request(UpdateTodoCompleteStatusApi(bean.id!!, 1))
            todoAdapter.remove(position)
        }
        if (todoAdapter.isEmpty()) {
            statusView.setStatus(Status.EMPTY)
        }
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
                    if (viewModel.todoStatus.value == 0) {
                        item.setIcon(R.drawable.ic_todo)
                        viewModel.todoStatus.value = 1
                    } else {
                        item.setIcon(R.drawable.ic_completed_todo)
                        viewModel.todoStatus.value = 0
                    }
                }
            }
            R.id.newTodo -> {
                if (viewModel.todoStatus.value == 0) {
                    // Show EditTodoDialog, NewTodoApi
                    EditTodoDialog().show(supportFragmentManager, EditTodoDialog::class.java.simpleName)
                } else {
                    showToast(R.string.please_switch_to_uncompleted_todo_page)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initData() {
        getTodoApi.resetPage()
        statusView.setStatus(Status.LOADING)
        httpManager.request(getTodoApi, todoListener)
    }
}
