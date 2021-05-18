package com.wkxjc.wanandroid.knowledgeTree.knowledgeTreeArticles

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.common.artical.LINK
import com.wkxjc.wanandroid.common.artical.WebActivity
import com.wkxjc.wanandroid.databinding.ActivityKnowledgeTreeArticlesBinding
import com.wkxjc.wanandroid.home.common.api.CollectApi
import com.wkxjc.wanandroid.home.common.api.KnowledgeTreeArticlesApi
import com.wkxjc.wanandroid.home.common.bean.ArticleBean
import com.wkxjc.wanandroid.me.common.api.HomePageCancelCollectionApi
import com.wkxjc.wanandroid.me.user.User


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
        knowledgeTreeArticlesAdapter.onItemClickListener = ::onItemClick
        binding.rvKnowledgeTreeArticles.adapter = knowledgeTreeArticlesAdapter
        statusView.setOnRetryBtnClickListener {
            initData()
        }
    }

    override fun initData() {
        statusView.setStatus(Status.LOADING)
        httpManager.request(knowledgeTreeArticlesApi, knowledgeTreeArticlesListener)
    }

    private fun onItemClick(view: View, bean: ArticleBean, position: Int) {
        when (view.id) {
            R.id.ivCollect -> {
                if (User.isNotLogon()) return
                httpManager.request(if (bean.collect) HomePageCancelCollectionApi(bean.id) else CollectApi(bean.id))
                bean.collect = !bean.collect
                knowledgeTreeArticlesAdapter.notifyItemChanged(position)
            }
            else -> myStartActivity<WebActivity>(LINK to bean.link)
        }
    }

    private fun loadMore() {
        httpManager.request(knowledgeTreeArticlesApi.apply { page++ }, knowledgeTreeArticlesLoadMoreListener)
    }
}
