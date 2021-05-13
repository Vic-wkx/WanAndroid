package com.wkxjc.wanandroid.knowledgeTree.knowledgeTreeArticles

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.databinding.ActivityKnowledgeTreeArticlesBinding
import com.wkxjc.wanandroid.home.common.api.KnowledgeTreeArticleApi


const val CATEGORY_ID = "categoryId"

class KnowledgeTreeArticlesActivity : BaseActivity() {
    private val binding by lazy { ActivityKnowledgeTreeArticlesBinding.inflate(layoutInflater) }

    override fun createBinding() = binding.root
    private val httpManager = HttpManager(this)
    private val knowledgeTreeArticleApi by lazy { KnowledgeTreeArticleApi(intent.extras!!.getInt(CATEGORY_ID)) }
    private val knowledgeTreeArticlesAdapter = KnowledgeTreeArticlesAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            knowledgeTreeArticlesAdapter.refresh(knowledgeTreeArticleApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }

    }

    override fun initView() {
        binding.rvKnowledgeTreeArticles.layoutManager = LinearLayoutManager(this)
        binding.rvKnowledgeTreeArticles.adapter = knowledgeTreeArticlesAdapter
    }

    override fun initData() {
        httpManager.request(knowledgeTreeArticleApi, listener)
    }

}
