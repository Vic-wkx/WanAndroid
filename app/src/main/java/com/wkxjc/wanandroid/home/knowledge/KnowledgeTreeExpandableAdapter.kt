package com.wkxjc.wanandroid.home.knowledge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.home.common.bean.Children
import com.wkxjc.wanandroid.home.common.bean.KnowledgeTreeBean
import com.wkxjc.wanandroid.home.common.bean.KnowledgeTrees
import kotlinx.android.synthetic.main.item_knowledge_tree_child.view.*
import kotlinx.android.synthetic.main.item_knowledge_tree_group.view.*

class KnowledgeTreeExpandableAdapter(private val knowledgeTrees: KnowledgeTrees = KnowledgeTrees()) : BaseExpandableListAdapter() {

    private lateinit var context: Context

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_knowledge_tree_group, parent, false)
        view.tvKnowledgeTreeGroupTitle.text = getGroup(groupPosition).name
        return view
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_knowledge_tree_child, parent, false)
        view.tvKnowledgeTreeChildTitle.text = getChild(groupPosition, childPosition).name
        return view
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