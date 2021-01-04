package com.wkxjc.wanandroid

import androidx.navigation.findNavController
import com.base.library.project.BaseActivity
import com.base.library.utils.FragmentUtils
import com.wkxjc.wanandroid.home.HomeFragment
import com.wkxjc.wanandroid.me.MeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val homeFragment by lazy { HomeFragment() }
    private val meFragment by lazy { MeFragment() }

    override fun layoutId() = R.layout.activity_main

    override fun initView() {

        FragmentUtils.add(supportFragmentManager, R.id.container, homeFragment, meFragment)
        FragmentUtils.show(supportFragmentManager, homeFragment)
        bottomMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    FragmentUtils.show(supportFragmentManager, homeFragment)
                }
                R.id.meFragment -> {
                    FragmentUtils.show(supportFragmentManager, meFragment)
                }
            }
            true
        }
    }

    override fun initData() {}

    override fun onSupportNavigateUp() = findNavController(R.id.container).navigateUp()
}
