package com.wkxjc.wanandroid.navigation

import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter

abstract class HeaderPinnedExpandableListAdapter : BaseExpandableListAdapter() {
    abstract fun onUpdateHeaderView(firstVisibleGroupPosition: Int, headerView: View?)
    abstract fun getHeaderView(parent: ViewGroup): View
}