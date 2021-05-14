package com.wkxjc.wanandroid.me.login.register

import android.util.Log
import com.base.library.project.BaseActivity
import com.base.library.project.toast
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.databinding.ActivityRegisterBinding
import com.wkxjc.wanandroid.me.common.api.RegisterApi


class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    private val httpManager = HttpManager(this)
    private val registerApi = RegisterApi()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            toast("注册成功")
            finish()
        }

        override fun onError(error: Throwable) {
            binding.btnRegister.isEnabled = true
        }
    }

    override fun initView() {
        binding.btnRegister.setOnClickListener {
            if (inputNotValid()) return@setOnClickListener
            binding.btnRegister.isEnabled = false
            httpManager.request(registerApi.apply {
                userName = binding.etRegisterUserName.text.toString()
                password = binding.etRegisterPassword.text.toString()
                confirmPassword = binding.etRegisterConfirmPassword.text.toString()
            }, listener)
        }
    }

    private fun inputNotValid(): Boolean {
        if (binding.etRegisterUserName.text.isEmpty()) {
            toast("User name cannot be empty!")
            return true
        }
        if (binding.etRegisterPassword.text.isEmpty() || binding.etRegisterConfirmPassword.text.isEmpty()) {
            toast("Password cannot be empty!")
            return true
        }
        if (binding.etRegisterPassword.text != binding.etRegisterConfirmPassword.text) {
            toast("两次输入的密码不一致！")
        }
        return false
    }

    override fun initData() {
    }
}
