package com.wkxjc.wanandroid

import com.base.library.project.BaseActivity
import com.base.library.project.myStartActivity
import com.wkxjc.wanandroid.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initView() {
        myStartActivity<MainActivity>()
        finish()
    }

    override fun initData() {
    }
}