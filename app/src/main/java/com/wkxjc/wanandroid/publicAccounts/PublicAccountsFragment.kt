package com.wkxjc.wanandroid.publicAccounts

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.base.library.project.BaseFragment
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsBinding


class PublicAccountsFragment : BaseFragment<FragmentPublicAccountsBinding>() {

    private val viewModel by activityViewModels<PublicAccountsViewModel>()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }

    override fun initView() {
        statusView.setOnRetryBtnClickListener {
            initData()
        }
        viewModel.publicAccountsStatus.observe(this, Observer {
            statusView.setStatus(it)
        })
    }

    override fun initData() {
        viewModel.publicAccountsStatus.value = Status.LOADING
    }
}
