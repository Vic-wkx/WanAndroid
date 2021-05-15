package com.wkxjc.wanandroid.publicAccounts

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsArticlesBinding
import com.wkxjc.wanandroid.publicAccounts.publicAccountsArticles.PublicAccountsArticlesAdapter

class PublicAccountsArticlesFragment : BaseFragment<FragmentPublicAccountsArticlesBinding>() {

    private val viewModel by activityViewModels<PublicAccountsViewModel>()

    private val publicAccountArticlesAdapter by lazy { PublicAccountsArticlesAdapter(viewModel.articles.value!!) }

    override fun initView() {
        binding.rvPublicAccountArticles.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountArticles.adapter = publicAccountArticlesAdapter
        viewModel.articles.observe(this) {
            publicAccountArticlesAdapter.refresh(it)
        }
    }

    override fun initData() {
    }
}