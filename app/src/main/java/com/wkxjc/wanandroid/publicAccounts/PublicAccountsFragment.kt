package com.wkxjc.wanandroid.publicAccounts

import androidx.fragment.app.activityViewModels
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsBinding
import com.wkxjc.wanandroid.home.common.api.PublicAccountsArticlesApi
import com.wkxjc.wanandroid.home.common.api.PublicAccountsAuthorsApi
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthors


class PublicAccountsFragment : BaseFragment<FragmentPublicAccountsBinding>() {

    private val viewModel by activityViewModels<PublicAccountsViewModel>()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }
    private val httpManager = HttpManager(this)
    private val publicAccountsAuthorsApi = PublicAccountsAuthorsApi()
    private val publicAccountArticlesApi by lazy { PublicAccountsArticlesApi() }
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
    private val publicAccountsArticlesListener = object : HttpListener() {
        override fun onNext(result: String) {
            viewModel.publicAccountsArticles.value = publicAccountArticlesApi.convert(result)
            viewModel.publicAccountsArticlesStatus.value = Status.NORMAL
            viewModel.publicAccountsStatus.value = Status.NORMAL
        }

        override fun onError(error: Throwable) {
            viewModel.publicAccountsArticlesStatus.value = Status.ERROR
            viewModel.publicAccountsStatus.value = Status.NORMAL
        }
    }

    override fun initView() {
        statusView.setOnRetryBtnClickListener {
            initData()
        }
        viewModel.publicAccountsStatus.observe(this, {
            statusView.setStatus(it)
        })
        viewModel.currentPublicAccountsAuthorId.observe(this, {
            viewModel.publicAccountsArticlesStatus.value = Status.LOADING
            httpManager.request(publicAccountArticlesApi.apply { id = it }, publicAccountsArticlesListener)
        })
    }

    override fun initData() {
        viewModel.publicAccountsStatus.value = Status.LOADING
        httpManager.request(publicAccountsAuthorsApi, publicAccountsAuthorsListener)
    }
}
