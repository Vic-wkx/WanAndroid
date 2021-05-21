package com.wkxjc.wanandroid.home

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
import com.wkxjc.wanandroid.common.api.ArticleApi
import com.wkxjc.wanandroid.common.api.BannerApi
import com.wkxjc.wanandroid.common.article.LINK
import com.wkxjc.wanandroid.common.article.WebActivity
import com.wkxjc.wanandroid.common.bean.ArticleBean
import com.wkxjc.wanandroid.common.bean.HomeBean
import com.wkxjc.wanandroid.databinding.FragmentHomeBinding
import com.wkxjc.wanandroid.me.MeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()
    private val meViewModel by activityViewModels<MeViewModel>()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }
    private val homeAdapter by lazy { HomeAdapter() }
    private val httpManager by lazy { HttpManager(this) }
    private val bannerApi by lazy { BannerApi() }
    private val articleApi by lazy { ArticleApi() }
    private val homeListListener by lazy {
        object : HttpListListener() {
            override fun onNext(resultMap: HashMap<BaseApi, Any>) {
                viewModel.isRefreshing.value = false
                val banners = bannerApi.convert(resultMap)
                val articles = articleApi.convert(resultMap)
                if (banners.data.isNullOrEmpty() && articles.datas.isNullOrEmpty()) {
                    viewModel.status.value = Status.EMPTY
                } else {
                    viewModel.homeBean.value = HomeBean(banners, articles)
                    viewModel.status.value = Status.NORMAL
                }
            }

            override fun onError(error: Throwable) {
                viewModel.isRefreshing.value = false
                viewModel.status.value = Status.ERROR
            }
        }
    }
    private val loadMoreListener by lazy {
        object : HttpListener() {
            override fun onNext(result: String) {
                homeAdapter.loadMore(articleApi.convert(result))
            }

            override fun onError(error: Throwable) {
                homeAdapter.isLoadingMore = false
                statusView.setStatus(Status.ERROR)
            }
        }
    }

    override var lazyLoad = false

    override fun initView() {
        viewModel.status.value = Status.LOADING
        homeAdapter.onItemClickListener = ::onItemClick
        homeAdapter.loadMore = ::loadMore
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = homeAdapter
        binding.refreshHome.setOnRefreshListener {
            initData()
        }
        statusView.setOnRetryBtnClickListener {
            viewModel.status.value = Status.LOADING
            initData()
        }
        viewModel.isRefreshing.observe(this, {
            binding.refreshHome.isRefreshing = it
        })
        viewModel.status.observe(this, {
            statusView.setStatus(it)
        })
        viewModel.homeBean.observe(this, {
            homeAdapter.refresh(it)
        })
    }

    override fun initData() {
        articleApi.resetPage()
        // order the api, so that there will not be two retrying subscriptions while no internet.
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