package com.wkxjc.wanandroid.me.todo

import android.view.Gravity
import androidx.fragment.app.activityViewModels
import com.base.library.project.BaseDialogFragment
import com.base.library.project.showToast
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.databinding.DialogConfimDeleteTodoBinding
import com.wkxjc.wanandroid.common.api.DeleteTodoApi
import com.wkxjc.wanandroid.me.todo.TodoViewModel

class ConfirmDeleteTodoDialog(val todoId: Int) : BaseDialogFragment<DialogConfimDeleteTodoBinding>() {
    private val httpManager by lazy { HttpManager(this) }
    private val viewModel by activityViewModels<TodoViewModel>()
    private val httpListener by lazy {
        object : HttpListener() {
            override fun onNext(result: String) {
                viewModel.dataChanged.value = true
                dismiss()
            }

            override fun onError(error: Throwable) {
                showToast(R.string.delete_fail)
            }
        }
    }

    override fun widthPercentage() = 70
    override fun heightPercentage() = 25
    override fun gravity() = Gravity.CENTER
    override fun initView() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.btnYes.setOnClickListener {
            httpManager.request(DeleteTodoApi(todoId), httpListener)
        }
        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }

    override fun initData() {
    }
}