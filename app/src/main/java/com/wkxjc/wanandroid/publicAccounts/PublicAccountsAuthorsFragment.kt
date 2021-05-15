package com.wkxjc.wanandroid.publicAccounts

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsAuthorsBinding

class PublicAccountsAuthorsFragment : BaseFragment<FragmentPublicAccountsAuthorsBinding>() {

    private val viewModel by activityViewModels<PublicAccountsViewModel>()
    private val publicAccountsAuthorsAdapter by lazy { PublicAccountsAuthorsAdapter(viewModel.publicAccountsAuthors.value!!) }

    override fun initView() {
        binding.rvPublicAccountsAuthors.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountsAuthors.adapter = publicAccountsAuthorsAdapter
        viewModel.publicAccountsAuthors.observe(this) {
            publicAccountsAuthorsAdapter.refresh(it.data)
        }
    }

    override fun initData() {
    }
}