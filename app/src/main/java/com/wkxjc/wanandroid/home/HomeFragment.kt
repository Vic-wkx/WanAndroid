package com.wkxjc.wanandroid.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.api.BaseApi
import com.base.library.rxRetrofit.http.httpList.HttpListListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.api.ArticleApi
import com.wkxjc.wanandroid.home.common.api.BannerApi
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val httpManager = HttpManager(this)
    private val bannerApi = BannerApi()
    private val articleApi = ArticleApi()
    private val homeAdapter = HomeAdapter()
    private val homeListListener = object : HttpListListener() {
        override fun onNext(resultMap: HashMap<BaseApi, Any>) {
            homeAdapter.refresh(bannerApi.convert(resultMap), articleApi.convert(resultMap))
        }

        override fun onError(error: Throwable) {
        }
    }

    override fun layoutId() = R.layout.fragment_home

    override fun initView() {
        rvHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
    }

    override fun initData() {
        httpManager.request(arrayOf(bannerApi, articleApi), homeListListener)
    }
}