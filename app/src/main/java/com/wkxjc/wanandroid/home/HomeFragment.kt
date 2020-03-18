package com.wkxjc.wanandroid.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.httpManager.BannerApi
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val httpManager by lazy { HttpManager(this) }
    private val bannerApi by lazy { BannerApi() }
    private val bannerListener by lazy { BannerListener() }

    override fun layoutId() = R.layout.fragment_home

    override fun initView() {
        rvHome.layoutManager = LinearLayoutManager(requireContext())
        rvHome.adapter = HomeAdapter(listOf("data"))
        btn.setOnClickListener {
            httpManager.request(bannerApi, bannerListener)
        }
    }

    override fun initData() {
    }
}