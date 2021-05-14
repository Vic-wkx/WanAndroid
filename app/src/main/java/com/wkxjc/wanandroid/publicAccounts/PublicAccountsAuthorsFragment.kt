package com.wkxjc.wanandroid.publicAccounts

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.lewis.widget.ui.Status
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsAuthorsBinding

class PublicAccountsAuthorsFragment : BaseFragment<FragmentPublicAccountsAuthorsBinding>() {

    private val viewModel by viewModels<PublicAccountsViewModel>()
    val publicAccountsAuthorsAdapter by lazy { PublicAccountsAuthorsAdapter(viewModel.publicAccountsAuthors) }

    override fun initView() {
        binding.rvPublicAccountsAuthors.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountsAuthors.adapter = publicAccountsAuthorsAdapter
        viewModel.status.observe(this) {
            Log.d("~~~", "get status:$it")
            if (it == Status.NORMAL) {
                publicAccountsAuthorsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun initData() {
    }
}