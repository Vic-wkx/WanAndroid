package com.wkxjc.wanandroid.me.login

import android.util.Log
import android.widget.Toast
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.common.api.LoginApi
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private val httpManager = HttpManager()
    private val loginApi = LoginApi()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            Log.d("~~~", "result: $result")
        }

        override fun onError(error: Throwable) {
            Log.d("~~~", "error:$error")
        }
    }

    override fun layoutId() = R.layout.activity_login

    override fun initView() {
        btnLogin.setOnClickListener {
            if (inputNotValid()) return@setOnClickListener
            loginApi.username = etUserName.text.toString()
            loginApi.password = etPassword.text.toString()
            httpManager.request(loginApi, listener)
        }
    }

    private fun inputNotValid(): Boolean {
        if (etUserName.text.isEmpty()) {
            Toast.makeText(this, "User name cannot be empty!", Toast.LENGTH_SHORT).show()
            return true
        }
        if (etPassword.text.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty!", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    override fun initData() {
    }

}
