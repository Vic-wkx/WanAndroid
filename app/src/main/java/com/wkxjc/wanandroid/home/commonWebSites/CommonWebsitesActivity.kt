package com.wkxjc.wanandroid.home.commonWebSites

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.api.CommonWebsitesApi
import kotlinx.android.synthetic.main.activity_common_websites.*

class CommonWebsitesActivity : BaseActivity() {
    private val httpManager = HttpManager(this)
    private val commonWebsitesApi = CommonWebsitesApi()
    private val commonWebsitesAdapter = CommonWebsitesAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            commonWebsitesAdapter.refresh(commonWebsitesApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }

    }

    override fun layoutId() = R.layout.activity_common_websites

    override fun initView() {
        rvCommonWebsites.layoutManager = LinearLayoutManager(this)
        rvCommonWebsites.adapter = commonWebsitesAdapter
    }

    override fun initData() {
        httpManager.request(commonWebsitesApi, listener)
    }
}
