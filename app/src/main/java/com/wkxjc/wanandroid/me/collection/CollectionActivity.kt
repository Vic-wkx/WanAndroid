package com.wkxjc.wanandroid.me.collection

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.common.article.LINK
import com.wkxjc.wanandroid.common.article.WebActivity
import com.wkxjc.wanandroid.databinding.ActivityCollectionBinding
import com.wkxjc.wanandroid.common.bean.CollectionBean
import com.wkxjc.wanandroid.common.api.CollectionApi
import com.wkxjc.wanandroid.common.api.CollectionPageCancelCollectionApi

class CollectionActivity : BaseActivity<ActivityCollectionBinding>() {
    private val collectionAdapter by lazy { CollectionAdapter() }
    private val statusView by lazy { StatusView.initInActivity(this) }
    private val httpManager = HttpManager(this)
    private val collectionApi by lazy { CollectionApi() }

    private val collectionsListener = object : HttpListener() {
        override fun onNext(result: String) {
            val collections = collectionApi.convert(result)
            collectionAdapter.refresh(collections)
            statusView.setStatus(if (collections.datas.isNotEmpty()) Status.NORMAL else Status.EMPTY)
        }

        override fun onError(error: Throwable) {
            statusView.setStatus(Status.ERROR)
        }
    }
    private val collectionsLoadMoreListener = object : HttpListener() {
        override fun onNext(result: String) {
            collectionAdapter.loadMore(collectionApi.convert(result))
        }

        override fun onError(error: Throwable) {
            statusView.setStatus(Status.ERROR)
        }
    }

    override fun initView() {
        binding.rvCollections.layoutManager = LinearLayoutManager(this)
        binding.rvCollections.adapter = collectionAdapter
        statusView.setOnRetryBtnClickListener {
            initData()
        }
        collectionAdapter.onItemClickListener = ::onItemClick
        collectionAdapter.loadMore = ::loadMore
    }

    override fun initData() {
        statusView.setStatus(Status.LOADING)
        httpManager.request(collectionApi, collectionsListener)
    }

    private fun onItemClick(view: View, bean: CollectionBean, position: Int) {
        when (view.id) {
            R.id.ivCancelCollect -> {
                httpManager.request(CollectionPageCancelCollectionApi(bean.id, bean.originId))
                collectionAdapter.removeItem(position)
                if (collectionAdapter.isEmpty()) {
                    statusView.setStatus(Status.EMPTY)
                }
            }
            else -> myStartActivity<WebActivity>(LINK to bean.link)
        }
    }

    private fun loadMore() {
        collectionApi.nextPage()
        httpManager.request(collectionApi, collectionsLoadMoreListener)
    }

}
