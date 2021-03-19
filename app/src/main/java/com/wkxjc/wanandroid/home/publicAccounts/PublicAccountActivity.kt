package com.wkxjc.wanandroid.home.publicAccounts

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.databinding.ActivityPublicAccountBinding
import com.wkxjc.wanandroid.home.common.api.PublicAccountApi


class PublicAccountActivity : BaseActivity() {

    private val binding by lazy { ActivityPublicAccountBinding.inflate(layoutInflater) }

    override fun createBinding() = binding.root

    private val httpManager = HttpManager(this)
    private val publicAccountApi = PublicAccountApi()
    private val publicAccountAdapter = PublicAccountAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            publicAccountAdapter.refresh(publicAccountApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }

    }

    override fun initView() {
        binding.rvPublicAccount.layoutManager = LinearLayoutManager(this)
        binding.rvPublicAccount.adapter = publicAccountAdapter
    }

    override fun initData() {
        httpManager.request(publicAccountApi, listener)
    }
}
