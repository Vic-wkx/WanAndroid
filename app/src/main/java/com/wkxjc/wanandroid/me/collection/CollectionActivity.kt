package com.wkxjc.wanandroid.me.collection

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.common.api.CollectionApi
import kotlinx.android.synthetic.main.activity_collection.*

class CollectionActivity : BaseActivity() {
    private val httpManager = HttpManager(this)
    private val collectionApi = CollectionApi()
    private val collectionAdapter = CollectionAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            collectionAdapter.refresh(collectionApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }

    }

    override fun layoutId() = R.layout.activity_collection

    override fun initView() {
        rvCollections.layoutManager = LinearLayoutManager(this)
        rvCollections.adapter = collectionAdapter
    }

    override fun initData() {
        httpManager.request(collectionApi, listener)
    }

}
