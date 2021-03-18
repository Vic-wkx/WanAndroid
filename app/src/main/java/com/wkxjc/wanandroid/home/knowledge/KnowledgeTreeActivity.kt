package com.wkxjc.wanandroid.home.knowledge

import com.base.library.project.BaseActivity
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.api.KnowledgeTreeApi
import com.wkxjc.wanandroid.home.knowledge.knowledgeTreeArticles.CATEGORY_ID
import com.wkxjc.wanandroid.home.knowledge.knowledgeTreeArticles.KnowledgeTreeArticlesActivity
import kotlinx.android.synthetic.main.activity_knowledge_tree.*

class KnowledgeTreeActivity : BaseActivity() {
    private val httpManager = HttpManager(this)
    private val knowledgeTreeApi = KnowledgeTreeApi()
    private val knowledgeTreeAdapter = KnowledgeTreeExpandableAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            knowledgeTreeAdapter.refresh(knowledgeTreeApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }
    }

    override fun layoutId() = R.layout.activity_knowledge_tree

    override fun initView() {
        elvKnowledgeTree.setAdapter(knowledgeTreeAdapter)
        elvKnowledgeTree.setOnChildClickListener { expandableListView, view, groupPosition, childPosition, childId ->
            myStartActivity<KnowledgeTreeArticlesActivity>(CATEGORY_ID to knowledgeTreeAdapter.getChild(groupPosition, childPosition).id)
            true
        }
    }

    override fun initData() {
        httpManager.request(knowledgeTreeApi, listener)
    }

}
