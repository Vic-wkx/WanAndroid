package com.wkxjc.wanandroid.me.login

import android.util.Log
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.user.User
import com.wkxjc.wanandroid.me.common.api.LoginApi
import com.wkxjc.wanandroid.me.login.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {

    private val httpManager = HttpManager(this)
    private val loginApi = LoginApi()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            Log.d("~~~", "result: $result")
            toast("login success")
            User.loginOn(loginApi.username)
            finish()
        }

        override fun onError(error: Throwable) {
            Log.d("~~~", "error:$error")
            btnLogin.isEnabled = true
        }
    }

    override fun layoutId() = R.layout.activity_login

    override fun initView() {
        btnLogin.setOnClickListener {
            if (inputNotValid()) return@setOnClickListener
            it.isEnabled = false
            loginApi.username = etLoginUserName.text.toString()
            loginApi.password = etLoginPassword.text.toString()
            httpManager.request(loginApi, listener)
        }
        btnGoToRegister.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }

    private fun inputNotValid(): Boolean {
        if (etLoginUserName.text.isEmpty()) {
            toast("User name cannot be empty!")
            return true
        }
        if (etLoginPassword.text.isEmpty()) {
            toast("Password cannot be empty!")
            return true
        }
        return false
    }

    override fun initData() {
    }

}
