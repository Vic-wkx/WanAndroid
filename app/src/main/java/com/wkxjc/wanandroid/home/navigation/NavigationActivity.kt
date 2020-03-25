package com.wkxjc.wanandroid.home.navigation

import com.base.library.project.BaseActivity
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.artical.LINK
import com.wkxjc.wanandroid.artical.WebActivity
import com.wkxjc.wanandroid.home.common.api.NavigationApi
import kotlinx.android.synthetic.main.activity_navigation.*
import org.jetbrains.anko.startActivity

class NavigationActivity : BaseActivity() {
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

    override fun layoutId() = R.layout.activity_navigation

    override fun initView() {
        elvNavigation.setAdapter(navigationExpandableAdapter)
        elvNavigation.setOnChildClickListener { expandableListView, view, groupPosition, childPosition, childId ->
            startActivity<WebActivity>(LINK to navigationExpandableAdapter.getChild(groupPosition, childPosition).link)
            true
        }
    }

    override fun initData() {
        httpManager.request(navigationApi, listener)
    }

}
