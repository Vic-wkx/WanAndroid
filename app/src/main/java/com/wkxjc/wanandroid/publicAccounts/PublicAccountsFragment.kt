package com.wkxjc.wanandroid.publicAccounts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.library.project.BaseFragment
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsBinding


class PublicAccountsFragment : BaseFragment() {

    private var _binding: FragmentPublicAccountsBinding? = null
    private val binding get() = _binding!!
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentPublicAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun releaseBinding() {
        _binding = null
    }

    override fun initView() {
    }

    override fun initData() {
    }
}
