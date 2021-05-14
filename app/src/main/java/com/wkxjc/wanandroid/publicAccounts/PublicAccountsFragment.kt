package com.wkxjc.wanandroid.publicAccounts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.base.library.project.BaseFragment
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsBinding


class PublicAccountsFragment : BaseFragment() {

    private var _binding: FragmentPublicAccountsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PublicAccountsViewModel>()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentPublicAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun releaseView() {
        _binding = null
    }

    override fun initView() {
        viewModel.status.observe(this) {
            statusView.setStatus(it)
        }
    }

    override fun initData() {
    }
}
