package com.wkxjc.wanandroid.navigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.library.project.BaseFragment
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.common.artical.LINK
import com.wkxjc.wanandroid.common.artical.WebActivity
import com.wkxjc.wanandroid.databinding.FragmentNavigationBinding
import com.wkxjc.wanandroid.home.common.api.NavigationApi


class NavigationFragment : BaseFragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun releaseView() {
        _binding = null
    }

    private val httpManager = HttpManager(this)
    private val navigationApi = NavigationApi()
    private val navigationExpandableAdapter = NavigationExpandableAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            navigationExpandableAdapter.refresh(navigationApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }
    }

    override fun initView() {
        binding.elvNavigation.setAdapter(navigationExpandableAdapter)
        binding.elvNavigation.setOnChildClickListener { expandableListView, view, groupPosition, childPosition, childId ->
            myStartActivity<WebActivity>(LINK to navigationExpandableAdapter.getChild(groupPosition, childPosition).link)
            true
        }
    }

    override fun initData() {
        httpManager.request(navigationApi, listener)
    }

}
