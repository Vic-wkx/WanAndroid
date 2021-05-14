package com.wkxjc.wanandroid.publicAccounts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsArticlesBinding
import com.wkxjc.wanandroid.home.common.api.PublicAccountsArticleApi
import com.wkxjc.wanandroid.publicAccounts.publicAccountsArticles.PublicAccountsArticlesAdapter

class PublicAccountsArticlesFragment : BaseFragment() {
    val authorId: Int = 0
    private var _binding: FragmentPublicAccountsArticlesBinding? = null
    private val binding get() = _binding!!
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentPublicAccountsArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun releaseBinding() {
        _binding = null
    }

    private val httpManager = HttpManager(this)
    private val publicAccountArticleApi by lazy { PublicAccountsArticleApi(authorId) }
    private val publicAccountArticlesAdapter = PublicAccountsArticlesAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            publicAccountArticlesAdapter.refresh(publicAccountArticleApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }
    }

    override fun initView() {
        binding.rvPublicAccountArticles.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountArticles.adapter = publicAccountArticlesAdapter
    }

    override fun initData() {
        httpManager.request(publicAccountArticleApi, listener)
    }
}