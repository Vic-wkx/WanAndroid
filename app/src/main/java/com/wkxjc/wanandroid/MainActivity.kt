package com.wkxjc.wanandroid

import com.base.library.project.BaseActivity
import com.base.library.utils.FragmentUtils
import com.wkxjc.wanandroid.databinding.ActivityMainBinding
import com.wkxjc.wanandroid.home.HomeFragment
import com.wkxjc.wanandroid.knowledgeTree.KnowledgeTreeFragment
import com.wkxjc.wanandroid.me.MeFragment
import com.wkxjc.wanandroid.navigation.NavigationFragment
import com.wkxjc.wanandroid.publicAccounts.PublicAccountsFragment


class MainActivity : BaseActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun createBinding() = binding.root

    private val homeFragment by lazy { HomeFragment() }
    private val publicAccountsFragment by lazy { PublicAccountsFragment() }
    private val knowledgeTreeFragment by lazy { KnowledgeTreeFragment() }
    private val navigationFragment by lazy { NavigationFragment() }
    private val meFragment by lazy { MeFragment() }

    override fun initView() {
        FragmentUtils.add(supportFragmentManager, R.id.container, homeFragment, publicAccountsFragment, navigationFragment, knowledgeTreeFragment, meFragment)
        FragmentUtils.show(supportFragmentManager, homeFragment)
        binding.bottomMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    FragmentUtils.show(supportFragmentManager, homeFragment)
                }
                R.id.publicAccountsFragment -> {
                    FragmentUtils.show(supportFragmentManager, publicAccountsFragment)
                }
                R.id.navigationFragment -> {
                    FragmentUtils.show(supportFragmentManager, navigationFragment)
                }
                R.id.knowledgeTreeFragment -> {
                    FragmentUtils.show(supportFragmentManager, knowledgeTreeFragment)
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
