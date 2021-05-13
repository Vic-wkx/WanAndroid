package com.wkxjc.wanandroid.publicAccounts.publicAccountsArticles

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.databinding.ActivityPublicAccountArticlesBinding
import com.wkxjc.wanandroid.home.common.api.PublicAccountArticleApi


class PublicAccountsArticlesActivity : BaseActivity() {

    private val binding by lazy { ActivityPublicAccountArticlesBinding.inflate(layoutInflater) }

    override fun createBinding() = binding.root
    private val httpManager = HttpManager(this)
    private val publicAccountArticleApi by lazy { PublicAccountArticleApi(intent.extras!!.getInt("id")) }
    private val publicAccountArticlesAdapter = PublicAccountsArticlesAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            publicAccountArticlesAdapter.refresh(publicAccountArticleApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }
    }

    override fun initView() {
        binding.rvPublicAccountArticles.layoutManager = LinearLayoutManager(this)
        binding.rvPublicAccountArticles.adapter = publicAccountArticlesAdapter
    }

    override fun initData() {
        httpManager.request(publicAccountArticleApi, listener)
    }
}
