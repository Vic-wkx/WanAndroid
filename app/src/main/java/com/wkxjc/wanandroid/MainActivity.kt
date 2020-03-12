package com.wkxjc.wanandroid

import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.base.library.project.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun layoutId() = R.layout.activity_main

    override fun initView() {
        bottomMenu.setupWithNavController(findNavController(R.id.navHostFragment))
    }

    override fun initData() {

    override fun onSupportNavigateUp() = findNavController(R.id.navHostFragment).navigateUp()
}
