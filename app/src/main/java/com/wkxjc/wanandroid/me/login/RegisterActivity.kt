package com.wkxjc.wanandroid.me.login

import com.base.library.project.BaseActivity
import com.base.library.project.showToast
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.databinding.ActivityRegisterBinding
import com.wkxjc.wanandroid.common.api.RegisterApi
import com.wkxjc.wanandroid.common.view.Status


class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    private val httpManager = HttpManager(this)
    private val registerApi = RegisterApi()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            showToast(R.string.register_successful)
            finish()
        }

        override fun onError(error: Throwable) {
            showToast(if (error.message == null) getString(R.string.register_fail) else error.message!!)
            binding.btnRegister.status = Status.NORMAL
        }
    }

    override fun initView() {
        binding.btnRegister.setOnClickListener {
            if (inputNotValid()) return@setOnClickListener
            binding.btnRegister.status = Status.LOADING
            httpManager.request(registerApi.apply {
                userName = binding.etRegisterUserName.text.toString()
                password = binding.etRegisterPassword.text.toString()
                confirmPassword = binding.etRegisterConfirmPassword.text.toString()
            }, listener)
        }
    }

    private fun inputNotValid(): Boolean {
        if (binding.etRegisterUserName.text.toString().isEmpty()) {
            showToast(R.string.user_name_cannot_be_empty)
            return true
        }
        if (binding.etRegisterPassword.text.toString().isEmpty() || binding.etRegisterConfirmPassword.text.toString().isEmpty()) {
            showToast(R.string.password_cannot_be_empty)
            return true
        }
        if (binding.etRegisterPassword.text.toString() != binding.etRegisterConfirmPassword.text.toString()) {
            showToast(R.string.password_did_not_match)
            return true
        }
        return false
    }

    override fun initData() {
    }
}
