package com.wkxjc.wanandroid.home.commonWebSites

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.databinding.ActivityCommonWebsitesBinding
import com.wkxjc.wanandroid.home.common.api.CommonWebsitesApi


class CommonWebsitesActivity : BaseActivity() {
    private val binding by lazy { ActivityCommonWebsitesBinding.inflate(layoutInflater) }

    override fun createBinding() = binding.root
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

    override fun initView() {
        binding.rvCommonWebsites.layoutManager = LinearLayoutManager(this)
        binding.rvCommonWebsites.adapter = commonWebsitesAdapter
    }

    override fun initData() {
        httpManager.request(commonWebsitesApi, listener)
    }
}
