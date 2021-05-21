package com.wkxjc.wanandroid.me.todo

import android.view.Gravity
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.base.library.project.BaseDialogFragment
import com.base.library.project.showToast
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.databinding.DialogEditTodoBinding
import com.wkxjc.wanandroid.common.view.Status
import com.wkxjc.wanandroid.common.api.AddTodoApi
import com.wkxjc.wanandroid.common.api.UpdateTodoApi
import com.wkxjc.wanandroid.common.bean.TodoBean

class EditTodoDialog(val bean: TodoBean? = null) : BaseDialogFragment<DialogEditTodoBinding>() {
    private val httpManager by lazy { HttpManager(this) }
    private val addTodoApi by lazy { AddTodoApi() }
    private val viewModel by activityViewModels<TodoViewModel>()
    private val addTodoListener by lazy {
        object : HttpListener() {
            override fun onNext(result: String) {
                viewModel.dataChanged.value = true
                dismiss()
            }

            override fun onError(error: Throwable) {
                showToast(R.string.add_fail)
                binding.btnSave.status = Status.NORMAL
            }
        }
    }
    private val updateTodoListener by lazy {
        object : HttpListener() {
            override fun onNext(result: String) {
                viewModel.dataChanged.value = true
                dismiss()
            }

            override fun onError(error: Throwable) {
                showToast(R.string.update_fail)
                binding.btnSave.status = Status.NORMAL
            }
        }
    }

    override fun widthPercentage() = ViewGroup.LayoutParams.MATCH_PARENT

    override fun heightPercentage() = 30

    override fun gravity() = Gravity.BOTTOM

    override fun initView() {
        binding.etTodo.setText(bean?.title)
        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.btnSave.setOnClickListener {
            if (checkContent()) return@setOnClickListener
            binding.btnSave.status = Status.LOADING
            if (bean == null) {
                httpManager.request(addTodoApi.apply { title = binding.etTodo.text.toString() }, addTodoListener)
            } else {
                httpManager.request(UpdateTodoApi(bean.apply {
                    title = binding.etTodo.text.toString()
                }), updateTodoListener)
            }
        }
    }

    private fun checkContent(): Boolean {
        if (binding.etTodo.text.isNullOrBlank()) {
            showToast(R.string.content_cannot_be_empty)
        }
        return binding.etTodo.text.isNullOrBlank()
    }

    override fun initData() {
    }

}