package com.wkxjc.wanandroid.publicAccounts.publicAccountsAuthors

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsAuthorsBinding
import com.wkxjc.wanandroid.publicAccounts.PublicAccountsViewModel

class PublicAccountsAuthorsFragment : BaseFragment<FragmentPublicAccountsAuthorsBinding>() {

    private val viewModel by activityViewModels<PublicAccountsViewModel>()
    private val publicAccountsAuthorsAdapter by lazy { PublicAccountsAuthorsAdapter() }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPublicAccountsAuthors.layoutManager = LinearLayoutManager(context)
        publicAccountsAuthorsAdapter.onItemClick = {
            viewModel.currentPublicAccountsAuthorId.value = it.id
        }
        binding.rvPublicAccountsAuthors.adapter = publicAccountsAuthorsAdapter
        viewModel.publicAccountsAuthors.observe(this) {
            publicAccountsAuthorsAdapter.refresh(it.data)
        }
    }
}