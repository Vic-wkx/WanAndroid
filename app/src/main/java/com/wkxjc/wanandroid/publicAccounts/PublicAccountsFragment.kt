package com.wkxjc.wanandroid.publicAccounts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountBinding
import com.wkxjc.wanandroid.home.common.api.PublicAccountApi


class PublicAccountsFragment : BaseFragment() {

    private var _binding: FragmentPublicAccountBinding? = null
    private val binding get() = _binding!!
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentPublicAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun releaseBinding() {
        _binding = null
    }

    private val httpManager = HttpManager(this)
    private val publicAccountApi = PublicAccountApi()
    private val publicAccountAdapter = PublicAccountsAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            publicAccountAdapter.refresh(publicAccountApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }

    }

    override fun initView() {
        binding.rvPublicAccount.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccount.adapter = publicAccountAdapter
    }

    override fun initData() {
        httpManager.request(publicAccountApi, listener)
    }
}
