package com.wkxjc.wanandroid.me.login

import android.view.Gravity
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.base.library.project.BaseDialogFragment
import com.base.library.project.myStartActivity
import com.base.library.project.showToast
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.databinding.DialogLoginBinding
import com.wkxjc.wanandroid.common.api.LoginApi
import com.wkxjc.wanandroid.common.view.Status
import com.wkxjc.wanandroid.me.MeViewModel
import com.wkxjc.wanandroid.common.user.NonTourist

class LoginDialog : BaseDialogFragment<DialogLoginBinding>() {

    private val httpManager = HttpManager(this)
    private val loginApi = LoginApi()
    private val meViewModel by activityViewModels<MeViewModel>()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            val loginBean = loginApi.convert(result)
            showToast(R.string.login_success)
            meViewModel.user.value?.loginOn(loginBean.publicName)
            meViewModel.user.value = NonTourist()
            dismiss()
        }

        override fun onError(error: Throwable) {
            showToast(R.string.login_fail)
            binding.btnLogin.status = Status.NORMAL
        }
    }

    override fun widthPercentage() = ViewGroup.LayoutParams.MATCH_PARENT
    override fun heightPercentage() = ViewGroup.LayoutParams.MATCH_PARENT
    override fun gravity() = Gravity.BOTTOM

    override fun initView() {
        binding.btnLogin.setOnClickListener {
            if (inputNotValid()) return@setOnClickListener
            binding.btnLogin.status = Status.LOADING
            loginApi.username = binding.etLoginUserName.text.toString()
            loginApi.password = binding.etLoginPassword.text.toString()
            httpManager.request(loginApi, listener)
        }
        binding.btnGoToRegister.setOnClickListener {
            myStartActivity<RegisterActivity>()
            dismiss()
        }
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    private fun inputNotValid(): Boolean {
        if (binding.etLoginUserName.text.isEmpty()) {
            showToast(R.string.user_name_cannot_be_empty)
            return true
        }
        if (binding.etLoginPassword.text.isEmpty()) {
            showToast(R.string.password_cannot_be_empty)
            return true
        }
        return false
    }

    override fun initData() {
    }

}