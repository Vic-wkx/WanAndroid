package com.wkxjc.wanandroid.me

import com.base.library.project.BaseFragment
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity

class MeFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_me

    override fun initView() {
        tvLogin.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }

    override fun initData() {
    }

}