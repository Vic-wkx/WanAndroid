package com.wkxjc.wanandroid.publicAccounts.publicAccountsAuthors

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsAuthorsBinding
import com.wkxjc.wanandroid.publicAccounts.PublicAccountsViewModel

class PublicAccountsAuthorsFragment : BaseFragment<FragmentPublicAccountsAuthorsBinding>() {

    private val viewModel by activityViewModels<PublicAccountsViewModel>()
    private val publicAccountsAuthorsAdapter by lazy { PublicAccountsAuthorsAdapter(viewModel.publicAccountsAuthors) }

    override fun initView() {
        binding.rvPublicAccountsAuthors.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountsAuthors.adapter = publicAccountsAuthorsAdapter
    }

    override fun initData() {
    }
}