package com.wkxjc.wanandroid.publicAccounts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsAuthorsBinding
import com.wkxjc.wanandroid.home.common.api.PublicAccountsAuthorApi

class PublicAccountsAuthorsFragment : BaseFragment() {
    private var _binding: FragmentPublicAccountsAuthorsBinding? = null
    private val binding get() = _binding!!
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentPublicAccountsAuthorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun releaseBinding() {
        _binding = null
    }

    private val viewModel by viewModels<PublicAccountsViewModel>()
    private val httpManager = HttpManager(this)
    private val publicAccountApi = PublicAccountsAuthorApi()
    private val publicAccountsAuthorsAdapter = PublicAccountsAuthorsAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            viewModel.publicAccountsAuthors.value?.refresh(publicAccountApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }

    }

    override fun initView() {
        binding.rvPublicAccountsAuthors.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountsAuthors.adapter = publicAccountsAuthorsAdapter
        viewModel.publicAccountsAuthors.observe(this) {
            publicAccountsAuthorsAdapter.refresh(it.data)
            if (it.data.isNotEmpty()) {
                // click the first item to show the articles

            }
        }
    }

    override fun initData() {
        httpManager.request(publicAccountApi, listener)
    }
}