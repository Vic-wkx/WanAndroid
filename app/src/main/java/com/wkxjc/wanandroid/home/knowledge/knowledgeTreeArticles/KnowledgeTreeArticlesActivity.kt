package com.wkxjc.wanandroid.home.knowledge.knowledgeTreeArticles

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.api.KnowledgeTreeArticleApi
import kotlinx.android.synthetic.main.activity_knowledge_tree_articles.*

const val CATEGORY_ID = "categoryId"

class KnowledgeTreeArticlesActivity : BaseActivity() {
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

    override fun layoutId() = R.layout.activity_knowledge_tree_articles

    override fun initView() {
        rvKnowledgeTreeArticles.layoutManager = LinearLayoutManager(this)
        rvKnowledgeTreeArticles.adapter = knowledgeTreeArticlesAdapter
    }

    override fun initData() {
        httpManager.request(knowledgeTreeArticleApi, listener)
    }

}
