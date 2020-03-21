package com.wkxjc.wanandroid.home.publicAccount.PublicAccountArticles

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.api.PublicAccountArticleApi
import kotlinx.android.synthetic.main.activity_public_account_articles.*

class PublicAccountArticlesActivity : BaseActivity() {

    private val httpManager = HttpManager(this)
    private val publicAccountArticleApi by lazy { PublicAccountArticleApi(intent.extras!!.getInt("id")) }
    private val publicAccountArticlesAdapter = PublicAccountArticlesAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            publicAccountArticlesAdapter.refresh(publicAccountArticleApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }
    }

    override fun layoutId() = R.layout.activity_public_account_articles

    override fun initView() {
        rvPublicAccountArticles.layoutManager = LinearLayoutManager(this)
        rvPublicAccountArticles.adapter = publicAccountArticlesAdapter
    }

    override fun initData() {
        httpManager.request(publicAccountArticleApi, listener)
    }
}
