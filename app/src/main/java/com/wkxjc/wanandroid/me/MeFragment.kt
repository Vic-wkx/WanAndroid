package com.wkxjc.wanandroid.me

import android.view.View
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.common.api.LOGIN_INFO
import com.wkxjc.wanandroid.me.common.api.PASSWORD
import com.wkxjc.wanandroid.me.common.api.USER_NAME
import com.wkxjc.wanandroid.me.user.User
import com.wkxjc.wanandroid.me.login.LoginActivity
import com.wkxjc.wanandroid.me.user.UserActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity

class MeFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_me

    override fun initView() {
        tvUser.setOnClickListener {
            startActivity<UserActivity>()
        }
        btnLogin.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }

    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
        if (User.isLogon) {
            tvUser.text = User.name
            tvUser.visibility = View.VISIBLE
            btnLogin.visibility = View.GONE
        } else {
            tvUser.visibility = View.GONE
            btnLogin.visibility = View.VISIBLE
        }
    }
}