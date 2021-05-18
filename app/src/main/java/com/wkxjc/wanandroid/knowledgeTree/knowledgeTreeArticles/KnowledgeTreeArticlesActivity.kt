package com.wkxjc.wanandroid.knowledgeTree.knowledgeTreeArticles

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.databinding.ActivityKnowledgeTreeArticlesBinding
import com.wkxjc.wanandroid.home.common.api.KnowledgeTreeArticlesApi


const val CATEGORY_ID = "categoryId"

class KnowledgeTreeArticlesActivity : BaseActivity<ActivityKnowledgeTreeArticlesBinding>() {
    private val httpManager = HttpManager(this)
    private val knowledgeTreeArticlesApi by lazy { KnowledgeTreeArticlesApi(intent.extras!!.getInt(CATEGORY_ID)) }
    private val statusView by lazy { StatusView.initInActivity(this) }
    private val knowledgeTreeArticlesAdapter = KnowledgeTreeArticlesAdapter()
    private val knowledgeTreeArticlesListener = object : HttpListener() {
        override fun onNext(result: String) {
            knowledgeTreeArticlesAdapter.refresh(knowledgeTreeArticlesApi.convert(result))
            statusView.setStatus(Status.NORMAL)
        }

        override fun onError(error: Throwable) {
            statusView.setStatus(Status.ERROR)
        }
    }

    private val knowledgeTreeArticlesLoadMoreListener = object : HttpListener() {
        override fun onNext(result: String) {
            knowledgeTreeArticlesAdapter.loadMore(knowledgeTreeArticlesApi.convert(result))
        }

        override fun onError(error: Throwable) {
            knowledgeTreeArticlesAdapter.isLoadingMore = false
            statusView.setStatus(Status.ERROR)
        }
    }

    override fun initView() {
        binding.rvKnowledgeTreeArticles.layoutManager = LinearLayoutManager(this)
        knowledgeTreeArticlesAdapter.loadMore = ::loadMore
        binding.rvKnowledgeTreeArticles.adapter = knowledgeTreeArticlesAdapter
        statusView.setOnRetryBtnClickListener {
            initData()
        }
    }

    override fun initData() {
        statusView.setStatus(Status.LOADING)
        httpManager.request(knowledgeTreeArticlesApi, knowledgeTreeArticlesListener)
    }

    fun loadMore() {
        httpManager.request(knowledgeTreeArticlesApi.apply { page++ }, knowledgeTreeArticlesLoadMoreListener)
    }
}
