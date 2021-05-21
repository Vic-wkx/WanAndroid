package com.wkxjc.wanandroid.common.view

import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter

abstract class HeaderPinnedExpandableListAdapter : BaseExpandableListAdapter() {
    abstract fun onUpdateHeaderView(firstVisibleGroupPosition: Int, headerView: View?)
    abstract fun getHeaderView(parent: ViewGroup): View
}