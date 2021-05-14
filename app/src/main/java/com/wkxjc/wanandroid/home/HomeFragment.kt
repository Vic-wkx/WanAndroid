package com.wkxjc.wanandroid.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
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
import com.wkxjc.wanandroid.home.common.api.CollectApi
import com.wkxjc.wanandroid.home.common.bean.ArticleBean
import com.wkxjc.wanandroid.me.common.api.HomePageCancelCollectionApi


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
//    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun releaseView() {
//        _binding = null
//    }

    private val viewModel by viewModels<HomeViewModel>()
    private val httpManager = HttpManager(this)
    private val bannerApi = BannerApi()
    private val articleApi = ArticleApi()
    private val collectApi = CollectApi()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }
    private val homePageCancelCollectionApi = HomePageCancelCollectionApi()
    private val homeAdapter by lazy { HomeAdapter(viewModel.homeBean.value!!) }
    private val collectListener = object : HttpListener() {
        override fun onNext(result: String) {
        }

        override fun onError(error: Throwable) {
        }
    }
    private val homePageCancelCollectionListener = object : HttpListener() {
        override fun onNext(result: String) {
        }

        override fun onError(error: Throwable) {
        }
    }
    private val homeListListener = object : HttpListListener() {
        override fun onNext(resultMap: HashMap<BaseApi, Any>) {
            viewModel.isRefreshing.value = false
            val banners = bannerApi.convert(resultMap)
            val articles = articleApi.convert(resultMap)
            if (banners.data.isNullOrEmpty() && articles.datas.isNullOrEmpty()) {
                viewModel.status.value = Status.EMPTY
            } else {
                viewModel.status.value = Status.NORMAL
                viewModel.homeBean.value?.refresh(banners, articles)
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
            homeAdapter.isLoadingMore = false
        }

        override fun onError(error: Throwable) {
            homeAdapter.isLoadingMore = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("~~~~", "onCreate")
        viewModel.status.value = Status.LOADING
        loadData()
    }

    override fun initView() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
        homeAdapter.onItemClickListener = ::onItemClick
        homeAdapter.loadMore = ::loadMore
        binding.refreshHome.setOnRefreshListener {
            loadData()
        }
        viewModel.isRefreshing.observe(this) {
            binding.refreshHome.isRefreshing = it
        }
        viewModel.homeBean.observe(this) {
            Log.d("~~~", "updated homeBean")
            homeAdapter.refresh(it.banners, it.articles)
        }
        viewModel.status.observe(this) {
            statusView.setStatus(it)
        }
        statusView.setOnRetryBtnClickListener {
            viewModel.status.value = Status.LOADING
            loadData()
        }
    }

    private fun onItemClick(view: View, bean: ArticleBean) {
        when (view.id) {
            R.id.ivCollect -> if (bean.collect) {
                httpManager.request(homePageCancelCollectionApi.apply {
                    articleId = bean.id
                }, homePageCancelCollectionListener)
            } else {
                httpManager.request(collectApi.apply {
                    articleId = bean.id
                }, collectListener)
            }
            else -> myStartActivity<WebActivity>(LINK to bean.link)
        }
    }

    override fun initData() {
    }

    private fun loadData() {
        articleApi.resetPage()
        // order the api, so that there will not be two subscriptions when retrying while no internet.
        httpManager.request(arrayOf(bannerApi, articleApi), homeListListener, HttpListConfig(order = true))
    }

    private fun loadMore() {
        articleApi.nextPage()
        httpManager.request(articleApi, loadMoreListener)
    }
}