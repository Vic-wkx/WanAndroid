package com.wkxjc.wanandroid.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.wkxjc.wanandroid.databinding.ItemNavigationChildBinding
import com.wkxjc.wanandroid.databinding.ItemNavigationGroupBinding
import com.wkxjc.wanandroid.home.common.bean.ArticleBean
import com.wkxjc.wanandroid.home.common.bean.NavigationBean
import com.wkxjc.wanandroid.home.common.bean.Navigations


class NavigationExpandableAdapter(private val navigations: Navigations = Navigations()) : BaseExpandableListAdapter() {

    private lateinit var context: Context

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        context = parent.context
        val binding = ItemNavigationGroupBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.tvNavigationGroupTitle.text = getGroup(groupPosition).name
        return binding.root
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemNavigationChildBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.tvNavigationChildTitle.text = getChild(groupPosition, childPosition).title
        return binding.root
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