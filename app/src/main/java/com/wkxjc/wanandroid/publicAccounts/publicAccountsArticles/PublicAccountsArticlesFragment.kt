package com.wkxjc.wanandroid.publicAccounts.publicAccountsArticles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.common.artical.LINK
import com.wkxjc.wanandroid.common.artical.WebActivity
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsArticlesBinding
import com.wkxjc.wanandroid.common.api.PublicAccountsArticlesApi
import com.wkxjc.wanandroid.common.bean.ArticleBean
import com.wkxjc.wanandroid.me.MeViewModel
import com.wkxjc.wanandroid.publicAccounts.PublicAccountsViewModel

class PublicAccountsArticlesFragment : BaseFragment<FragmentPublicAccountsArticlesBinding>() {
    private val viewModel by activityViewModels<PublicAccountsViewModel>()
    private val publicAccountArticlesAdapter by lazy { PublicAccountsArticlesAdapter() }
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }
    private val httpManager = HttpManager(this)
    private val publicAccountArticlesApi by lazy { PublicAccountsArticlesApi() }
    private val meViewModel by activityViewModels<MeViewModel>()

    private val publicAccountsArticlesListener = object : HttpListener() {
        override fun onNext(result: String) {
            viewModel.publicAccountsArticles.value = publicAccountArticlesApi.convert(result)
            viewModel.publicAccountsArticlesStatus.value = Status.NORMAL
            viewModel.publicAccountsStatus.value = Status.NORMAL
            binding.rvPublicAccountArticles.scrollToPosition(0)
        }

        override fun onError(error: Throwable) {
            viewModel.publicAccountsArticlesStatus.value = Status.ERROR
            viewModel.publicAccountsStatus.value = Status.NORMAL
        }
    }
    private val publicAccountsArticlesLoadMoreListener = object : HttpListener() {
        override fun onNext(result: String) {
            publicAccountArticlesAdapter.loadMore(publicAccountArticlesApi.convert(result))
        }

        override fun onError(error: Throwable) {
            viewModel.publicAccountsArticlesStatus.value = Status.ERROR
        }
    }

    override var lazyLoad = false

    override fun initView() {
        binding.rvPublicAccountArticles.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountArticles.adapter = publicAccountArticlesAdapter
        statusView.setOnRetryBtnClickListener {
            // Let LiveData refresh
            viewModel.currentPublicAccountsAuthorId.value = viewModel.currentPublicAccountsAuthorId.value
        }
        publicAccountArticlesAdapter.onItemClickListener = ::onItemClick
        publicAccountArticlesAdapter.loadMore = {
            viewModel.currentPublicAccountsArticlesPage.value = viewModel.currentPublicAccountsArticlesPage.value!! + 1
        }
        val observe = viewModel.publicAccountsArticles.observe(viewLifecycleOwner) {
            publicAccountArticlesAdapter.refresh(it)
        }
        viewModel.publicAccountsArticlesStatus.observe(viewLifecycleOwner) {
            statusView.setStatus(it)
        }

        viewModel.currentPublicAccountsAuthorId.observe(viewLifecycleOwner) {
            viewModel.publicAccountsArticlesStatus.value = Status.LOADING
            viewModel.currentPublicAccountsArticlesPage.value = 0
            httpManager.request(publicAccountArticlesApi.apply {
                id = it
                page = 0
            }, publicAccountsArticlesListener)
        }
        viewModel.currentPublicAccountsArticlesPage.observe(viewLifecycleOwner) {
            if (it == 0) return@observe
            httpManager.request(publicAccountArticlesApi.apply {
                page = it
            }, publicAccountsArticlesLoadMoreListener)
        }
    }

    override fun initData() {
    }

    private fun onItemClick(view: View, bean: ArticleBean, position: Int) {
        when (view.id) {
            R.id.ivCollect -> meViewModel.user.value?.onClickCollect(httpManager, bean, publicAccountArticlesAdapter, position)
            else -> myStartActivity<WebActivity>(LINK to bean.link)
        }
    }
}