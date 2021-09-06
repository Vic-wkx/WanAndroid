package com.wkxjc.wanandroid

import android.view.MenuItem
import com.base.library.project.BaseActivity
import com.base.library.utils.FragmentUtils
import com.wkxjc.wanandroid.databinding.ActivityMainBinding
import com.wkxjc.wanandroid.home.HomeFragment
import com.wkxjc.wanandroid.knowledgeTree.KnowledgeTreeFragment
import com.wkxjc.wanandroid.me.MeFragment
import com.wkxjc.wanandroid.navigation.NavigationFragment
import com.wkxjc.wanandroid.publicAccounts.PublicAccountsFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val homeFragment by lazy { HomeFragment() }
    private val publicAccountsFragment by lazy { PublicAccountsFragment() }
    private val knowledgeTreeFragment by lazy { KnowledgeTreeFragment() }
    private val navigationFragment by lazy { NavigationFragment() }
    private val meFragment by lazy { MeFragment() }

    override fun initView() {
        FragmentUtils.add(supportFragmentManager, R.id.container, homeFragment, publicAccountsFragment, navigationFragment, knowledgeTreeFragment, meFragment)
        FragmentUtils.show(supportFragmentManager, homeFragment)
        binding.bottomMenu.setOnNavigationItemSelectedListener {
            FragmentUtils.show(supportFragmentManager, findMatchedFragment(it))
            true
        }
    }

    private fun findMatchedFragment(menuItem: MenuItem) = when (menuItem.itemId) {
        R.id.homeFragment -> homeFragment
        R.id.publicAccountsFragment -> publicAccountsFragment
        R.id.navigationFragment -> navigationFragment
        R.id.knowledgeTreeFragment -> knowledgeTreeFragment
        R.id.meFragment -> meFragment
        else -> throw Exception("Impossible")
    }

    override fun initData() {}
}
