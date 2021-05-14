package com.wkxjc.wanandroid.publicAccounts

import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.base.library.project.BaseFragment
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsBinding


class PublicAccountsFragment : BaseFragment<FragmentPublicAccountsBinding>() {

    private val viewModel by viewModels<PublicAccountsViewModel>()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }

    override fun initView() {
        viewModel.status.observe(this) {
            statusView.setStatus(it)
        }
    }

    override fun initData() {
    }
}
