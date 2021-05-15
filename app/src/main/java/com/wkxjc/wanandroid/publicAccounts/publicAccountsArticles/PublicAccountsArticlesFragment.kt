package com.wkxjc.wanandroid.publicAccounts.publicAccountsArticles

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsArticlesBinding
import com.wkxjc.wanandroid.publicAccounts.PublicAccountsViewModel

class PublicAccountsArticlesFragment : BaseFragment<FragmentPublicAccountsArticlesBinding>() {

    private val viewModel by activityViewModels<PublicAccountsViewModel>()

    private val publicAccountArticlesAdapter by lazy { PublicAccountsArticlesAdapter(viewModel.articles) }

    override fun initView() {
        binding.rvPublicAccountArticles.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountArticles.adapter = publicAccountArticlesAdapter
    }

    override fun initData() {
    }
}