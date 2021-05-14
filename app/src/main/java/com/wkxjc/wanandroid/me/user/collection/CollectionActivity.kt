package com.wkxjc.wanandroid.me.user.collection

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.common.artical.LINK
import com.wkxjc.wanandroid.common.artical.WebActivity
import com.wkxjc.wanandroid.databinding.ActivityCollectionBinding
import com.wkxjc.wanandroid.home.common.bean.CollectionBean
import com.wkxjc.wanandroid.me.common.api.CollectionPageCancelCollectionApi
import com.wkxjc.wanandroid.me.common.api.CollectionApi


class CollectionActivity : BaseActivity<ActivityCollectionBinding>() {
    private val httpManager = HttpManager(this)
    private val collectionApi = CollectionApi()
    private val cancelCollectionApi = CollectionPageCancelCollectionApi()
    private val collectionAdapter = CollectionAdapter()
    private val cancelCollectionListener = object : HttpListener() {
        override fun onNext(result: String) {
            Log.d("~~~", "result: $result")
        }

        override fun onError(error: Throwable) {
        }
    }
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            collectionAdapter.refresh(collectionApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }

    }

    override fun initView() {
        binding.rvCollections.layoutManager = LinearLayoutManager(this)
        binding.rvCollections.adapter = collectionAdapter
        collectionAdapter.onItemClickListener = object : CollectionAdapter.OnItemClickListener {
            override fun onItemClick(view: View, bean: CollectionBean) {
                when (view.id) {
                    R.id.tvCancelCollect -> httpManager.request(cancelCollectionApi.apply {
                        articleId = bean.id
                        originId = bean.originId
                    }, cancelCollectionListener)
                    else -> myStartActivity<WebActivity>(LINK to bean.link)
                }
            }
        }
    }

    override fun initData() {
        httpManager.request(collectionApi, listener)
    }

}
