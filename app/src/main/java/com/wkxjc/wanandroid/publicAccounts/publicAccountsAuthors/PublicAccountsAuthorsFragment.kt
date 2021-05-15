package com.wkxjc.wanandroid.publicAccounts.publicAccountsAuthors

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsAuthorsBinding
import com.wkxjc.wanandroid.home.common.api.PublicAccountsAuthorsApi
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthors
import com.wkxjc.wanandroid.publicAccounts.PublicAccountsViewModel

class PublicAccountsAuthorsFragment : BaseFragment<FragmentPublicAccountsAuthorsBinding>() {

    private val viewModel by activityViewModels<PublicAccountsViewModel>()
    private val publicAccountsAuthorsAdapter by lazy { PublicAccountsAuthorsAdapter() }
    private val httpManager = HttpManager(this)
    private val publicAccountsAuthorsApi = PublicAccountsAuthorsApi()
    private val publicAccountsAuthorsListener = object : HttpListener() {
        override fun onNext(result: String) {
            val authors = PublicAccountsAuthors(publicAccountsAuthorsApi.convert(result).toMutableList())
            viewModel.publicAccountsAuthors.value = authors
            viewModel.currentPublicAccountsAuthorId.value = authors.data.first().id
        }

        override fun onError(error: Throwable) {
            viewModel.publicAccountsStatus.value = Status.ERROR
        }
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPublicAccountsAuthors.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountsAuthors.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        publicAccountsAuthorsAdapter.onItemClick = {
            viewModel.currentPublicAccountsAuthorId.value = it.id
        }
        binding.rvPublicAccountsAuthors.adapter = publicAccountsAuthorsAdapter
        viewModel.publicAccountsAuthors.observe(this, {
            publicAccountsAuthorsAdapter.refresh(it.data)
        })
        viewModel.publicAccountsStatus.observe(this, {
            if (it == Status.LOADING) {
                httpManager.request(publicAccountsAuthorsApi, publicAccountsAuthorsListener)
            }
        })
    }
}