package com.wkxjc.wanandroid.me.login.register

import android.util.Log
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.common.api.RegisterApi
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity() {
    private val httpManager = HttpManager(this)
    private val registerApi = RegisterApi()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            Log.d("~~~", "result: $result")
            toast("注册成功")
            finish()
        }

        override fun onError(error: Throwable) {
            Log.d("~~~", "error:$error")
            btnRegister.isEnabled = true
        }
    }

    override fun layoutId() = R.layout.activity_register

    override fun initView() {
        btnRegister.setOnClickListener {
            if (inputNotValid()) return@setOnClickListener
            btnRegister.isEnabled = false
            httpManager.request(registerApi.apply {
                userName = etRegisterUserName.text.toString()
                password = etRegisterPassword.text.toString()
                confirmPassword = etRegisterConfirmPassword.text.toString()
            }, listener)
        }
    }

    private fun inputNotValid(): Boolean {
        if (etRegisterUserName.text.isEmpty()) {
            toast("User name cannot be empty!")
            return true
        }
        if (etRegisterPassword.text.isEmpty() || etRegisterConfirmPassword.text.isEmpty()) {
            toast("Password cannot be empty!")
            return true
        }
        if (etRegisterPassword.text != etRegisterConfirmPassword.text) {
            toast("两次输入的密码不一致！")
        }
        return false
    }

    override fun initData() {
    }
}
