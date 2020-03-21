package com.wkxjc.wanandroid.home.publicAccount

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.api.PublicAccountApi
import kotlinx.android.synthetic.main.activity_public_account.*

class PublicAccountActivity : BaseActivity() {

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

    override fun layoutId() = R.layout.activity_public_account

    override fun initView() {
        rvPublicAccount.layoutManager = LinearLayoutManager(this)
        rvPublicAccount.adapter = publicAccountAdapter
    }

    override fun initData() {
        httpManager.request(publicAccountApi, listener)
    }
}
