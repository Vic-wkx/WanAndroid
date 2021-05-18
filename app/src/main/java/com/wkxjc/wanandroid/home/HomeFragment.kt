package com.wkxjc.wanandroid.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.api.BaseApi
import com.base.library.rxRetrofit.http.httpList.HttpListConfig
import com.base.library.rxRetrofit.http.httpList.HttpListListener
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.common.artical.LINK
import com.wkxjc.wanandroid.common.artical.WebActivity
import com.wkxjc.wanandroid.databinding.FragmentHomeBinding
import com.wkxjc.wanandroid.home.common.api.ArticleApi
import com.wkxjc.wanandroid.home.common.api.BannerApi
import com.wkxjc.wanandroid.home.common.bean.ArticleBean
import com.wkxjc.wanandroid.home.common.bean.HomeBean
import com.wkxjc.wanandroid.me.MeViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()
    private val httpManager = HttpManager(this)
    private val bannerApi = BannerApi()
    private val articleApi = ArticleApi()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }
    private val homeAdapter by lazy { HomeAdapter() }
    private val meViewModel by activityViewModels<MeViewModel>()

    private val homeListListener = object : HttpListListener() {
        override fun onNext(resultMap: HashMap<BaseApi, Any>) {
            viewModel.isRefreshing.value = false
            val banners = bannerApi.convert(resultMap)
            val articles = articleApi.convert(resultMap)
            if (banners.data.isNullOrEmpty() && articles.datas.isNullOrEmpty()) {
                viewModel.status.value = Status.EMPTY
            } else {
                viewModel.status.value = Status.NORMAL
                viewModel.homeBean.value = HomeBean(banners, articles)
            }
        }

        override fun onError(error: Throwable) {
            viewModel.isRefreshing.value = false
            viewModel.status.value = Status.ERROR
        }
    }

    private val loadMoreListener = object : HttpListener() {
        override fun onNext(result: String) {
            homeAdapter.addMore(articleApi.convert(result))
        }

        override fun onError(error: Throwable) {
            homeAdapter.isLoadingMore = false
            statusView.setStatus(Status.ERROR)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.status.value = Status.LOADING
    }

    override fun initView() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
        homeAdapter.onItemClickListener = ::onItemClick
        homeAdapter.loadMore = ::loadMore
        binding.refreshHome.setOnRefreshListener {
            initData()
        }
        statusView.setOnRetryBtnClickListener {
            viewModel.status.value = Status.LOADING
            initData()
        }
        viewModel.isRefreshing.observe(this, Observer {
            binding.refreshHome.isRefreshing = it
        })
        viewModel.status.observe(this, Observer {
            statusView.setStatus(it)
        })
        viewModel.homeBean.observe(this, Observer {
            homeAdapter.refresh(it)
        })
    }

    override fun initData() {
        articleApi.resetPage()
        // order the api, so that there will not be two subscriptions when retrying while no internet.
        httpManager.request(arrayOf(bannerApi, articleApi), homeListListener, HttpListConfig(order = true))
    }

    private fun onItemClick(view: View, bean: ArticleBean, position: Int) {
        when (view.id) {
            R.id.ivCollect -> meViewModel.user.value?.onClickCollect(httpManager, bean, homeAdapter, position)
            else -> myStartActivity<WebActivity>(LINK to bean.link)
        }
    }

    private fun loadMore() {
        articleApi.nextPage()
        httpManager.request(articleApi, loadMoreListener)
    }
}