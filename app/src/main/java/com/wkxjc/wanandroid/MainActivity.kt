package com.wkxjc.wanandroid

import com.base.library.project.BaseActivity
import com.base.library.utils.FragmentUtils
import com.wkxjc.wanandroid.databinding.ActivityMainBinding
import com.wkxjc.wanandroid.home.HomeFragment
import com.wkxjc.wanandroid.me.MeFragment


class MainActivity : BaseActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun createBinding() = binding.root

    private val homeFragment by lazy { HomeFragment() }
    private val meFragment by lazy { MeFragment() }

    override fun initView() {
        FragmentUtils.add(supportFragmentManager, R.id.container, homeFragment, meFragment)
        FragmentUtils.show(supportFragmentManager, homeFragment)
        binding.bottomMenu.setOnNavigationItemSelectedListener {
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
}
