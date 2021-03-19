package com.wkxjc.wanandroid

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.base.library.project.BaseActivity
import com.wkxjc.wanandroid.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun createBinding() = binding.root

    override fun initView() {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment? ?: return
        binding.bottomMenu.setupWithNavController(host.navController)
    }

    override fun initData() {}

    override fun onSupportNavigateUp() = findNavController(R.id.navHostFragment).navigateUp()
}
