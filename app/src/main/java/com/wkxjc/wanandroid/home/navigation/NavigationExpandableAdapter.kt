package com.wkxjc.wanandroid.home.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.bean.ArticleBean
import com.wkxjc.wanandroid.home.common.bean.NavigationBean
import com.wkxjc.wanandroid.home.common.bean.Navigations
import kotlinx.android.synthetic.main.item_navigation_child.view.*
import kotlinx.android.synthetic.main.item_navigation_group.view.*

class NavigationExpandableAdapter(private val navigations: Navigations = Navigations()) : BaseExpandableListAdapter() {

    private lateinit var context: Context

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_navigation_group, parent, false)
        view.tvNavigationGroupTitle.text = getGroup(groupPosition).name
        return view
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_navigation_child, parent, false)
        view.tvNavigationChildTitle.text = getChild(groupPosition, childPosition).title
        return view
    }

    fun refresh(navigations: Navigations) {
        this.navigations.refresh(navigations)
        notifyDataSetChanged()
    }

    override fun getGroup(groupPosition: Int): NavigationBean = navigations.data[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): ArticleBean = navigations.data[groupPosition].articles[childPosition]

    override fun getGroupCount(): Int = navigations.data.size

    override fun getChildrenCount(groupPosition: Int): Int = navigations.data[groupPosition].articles.size

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    override fun hasStableIds(): Boolean = false
}