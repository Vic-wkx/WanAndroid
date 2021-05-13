package com.wkxjc.wanandroid.knowledgeTree

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.wkxjc.wanandroid.databinding.ItemKnowledgeTreeChildBinding
import com.wkxjc.wanandroid.databinding.ItemKnowledgeTreeGroupBinding
import com.wkxjc.wanandroid.home.common.bean.Children
import com.wkxjc.wanandroid.home.common.bean.KnowledgeTreeBean
import com.wkxjc.wanandroid.home.common.bean.KnowledgeTrees


class KnowledgeTreeExpandableAdapter(private val knowledgeTrees: KnowledgeTrees = KnowledgeTrees()) : BaseExpandableListAdapter() {

    private lateinit var context: Context

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        context = parent.context
        val binding = ItemKnowledgeTreeGroupBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.tvKnowledgeTreeGroupTitle.text = getGroup(groupPosition).name
        return binding.root
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemKnowledgeTreeChildBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.tvKnowledgeTreeChildTitle.text = getChild(groupPosition, childPosition).name
        return binding.root
    }

    fun refresh(knowledgeTrees: KnowledgeTrees) {
        this.knowledgeTrees.refresh(knowledgeTrees)
        notifyDataSetChanged()
    }

    override fun getGroup(groupPosition: Int): KnowledgeTreeBean = knowledgeTrees.data[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Children = knowledgeTrees.data[groupPosition].children[childPosition]

    override fun getGroupCount(): Int = knowledgeTrees.data.size

    override fun getChildrenCount(groupPosition: Int): Int = knowledgeTrees.data[groupPosition].children.size

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    override fun hasStableIds(): Boolean = false
}