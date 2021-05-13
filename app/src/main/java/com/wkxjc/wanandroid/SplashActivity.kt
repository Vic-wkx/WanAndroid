package com.wkxjc.wanandroid

import com.base.library.project.BaseActivity
import com.base.library.project.myStartActivity
import com.wkxjc.wanandroid.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    override fun createBinding() = binding.root

    override fun initView() {
        myStartActivity<MainActivity>()
        finish()
    }

    override fun initData() {
    }
}