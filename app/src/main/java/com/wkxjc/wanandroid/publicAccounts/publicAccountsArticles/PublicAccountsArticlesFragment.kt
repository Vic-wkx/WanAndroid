package com.wkxjc.wanandroid.publicAccounts.publicAccountsArticles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsArticlesBinding
import com.wkxjc.wanandroid.publicAccounts.PublicAccountsViewModel

class PublicAccountsArticlesFragment : BaseFragment<FragmentPublicAccountsArticlesBinding>() {

    private val viewModel by activityViewModels<PublicAccountsViewModel>()
    private val publicAccountArticlesAdapter by lazy { PublicAccountsArticlesAdapter() }
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPublicAccountArticles.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountArticles.adapter = publicAccountArticlesAdapter
        statusView.setOnRetryBtnClickListener {
            // Let LiveData refresh
            viewModel.currentPublicAccountsAuthorId.value = viewModel.currentPublicAccountsAuthorId.value
        }
        viewModel.publicAccountsArticles.observe(this, {
            publicAccountArticlesAdapter.refresh(it)
            binding.rvPublicAccountArticles.scrollToPosition(0)
        })
        viewModel.publicAccountsArticlesStatus.observe(this, {
            statusView.setStatus(it)
        })
    }
}