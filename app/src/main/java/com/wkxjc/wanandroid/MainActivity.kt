package com.wkxjc.wanandroid

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.base.library.project.BaseActivity
import com.wkxjc.wanandroid.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun createBinding() = binding.root

    private val homeFragment by lazy { HomeFragment() }
    private val meFragment by lazy { MeFragment() }

    override fun layoutId() = R.layout.activity_main

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
