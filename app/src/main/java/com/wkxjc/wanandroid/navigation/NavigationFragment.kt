package com.wkxjc.wanandroid.navigation

import com.base.library.project.BaseFragment
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.common.article.LINK
import com.wkxjc.wanandroid.common.article.WebActivity
import com.wkxjc.wanandroid.databinding.FragmentNavigationBinding
import com.wkxjc.wanandroid.common.api.NavigationApi


class NavigationFragment : BaseFragment<FragmentNavigationBinding>() {

    private val httpManager = HttpManager(this)
    private val navigationApi = NavigationApi()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }
    private val navigationExpandableAdapter by lazy { NavigationExpandableAdapter(context) }
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            statusView.setStatus(Status.NORMAL)
            navigationExpandableAdapter.refresh(navigationApi.convert(result))
            // Expand all groups
            repeat(navigationExpandableAdapter.groupCount) {
                binding.elvNavigation.expandableListView?.expandGroup(it)
            }
        }

        override fun onError(error: Throwable) {
            statusView.setStatus(Status.ERROR)
        }
    }

    override fun initView() {
        binding.elvNavigation.setAdapter(navigationExpandableAdapter)
        binding.elvNavigation.expandableListView?.setOnChildClickListener { expandableListView, view, groupPosition, childPosition, childId ->
            myStartActivity<WebActivity>(LINK to navigationExpandableAdapter.getChild(groupPosition, childPosition).link)
            true
        }
        statusView.setOnRetryBtnClickListener {
            initData()
        }
    }


    override fun initData() {
        statusView.setStatus(Status.LOADING)
        httpManager.request(navigationApi, listener)
    }
}
