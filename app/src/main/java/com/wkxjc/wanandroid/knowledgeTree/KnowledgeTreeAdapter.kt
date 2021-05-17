package com.wkxjc.wanandroid.knowledgeTree

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.library.project.myStartActivity
import com.wkxjc.wanandroid.databinding.ItemKnowledgeTreeChildBinding
import com.wkxjc.wanandroid.databinding.ItemKnowledgeTreeGroupBinding
import com.wkxjc.wanandroid.home.common.bean.DisplayedKnowledgeTreeBean
import com.wkxjc.wanandroid.home.common.bean.DisplayedKnowledgeTreeBeanFactory
import com.wkxjc.wanandroid.home.common.bean.KnowledgeTreeType
import com.wkxjc.wanandroid.home.common.bean.KnowledgeTrees
import com.wkxjc.wanandroid.knowledgeTree.knowledgeTreeArticles.CATEGORY_ID
import com.wkxjc.wanandroid.knowledgeTree.knowledgeTreeArticles.KnowledgeTreeArticlesActivity

class KnowledgeTreeAdapter(val displayedKnowledgeTrees: MutableList<DisplayedKnowledgeTreeBean> = mutableListOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            KnowledgeTreeType.GROUP.intValue -> {
                GroupViewHolder(ItemKnowledgeTreeGroupBinding.inflate(LayoutInflater.from(context), parent, false))
            }
            KnowledgeTreeType.CHILD.intValue -> {
                ChildViewHolder(ItemKnowledgeTreeChildBinding.inflate(LayoutInflater.from(context), parent, false))
            }
            else -> {
                throw Exception("Unsupported view type")
            }
        }
    }

    inner class GroupViewHolder(val binding: ItemKnowledgeTreeGroupBinding) : RecyclerView.ViewHolder(binding.root)
    inner class ChildViewHolder(val binding: ItemKnowledgeTreeChildBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GroupViewHolder -> {
                holder.binding.tvKnowledgeTreeGroupTitle.text = displayedKnowledgeTrees[position].name
            }
            is ChildViewHolder -> {
                holder.binding.btnKnowledgeTreeChildTitle.text = displayedKnowledgeTrees[position].name
                holder.binding.btnKnowledgeTreeChildTitle.setOnClickListener {
                    context.myStartActivity<KnowledgeTreeArticlesActivity>(CATEGORY_ID to displayedKnowledgeTrees[position].id)
                }
            }
        }
    }

    override fun getItemCount() = displayedKnowledgeTrees.size

    override fun getItemViewType(position: Int): Int {
        return displayedKnowledgeTrees[position].type.intValue
    }

    fun refresh(knowledgeTrees: KnowledgeTrees) {
        displayedKnowledgeTrees.clear()
        knowledgeTrees.data.forEach {
            displayedKnowledgeTrees.add(DisplayedKnowledgeTreeBeanFactory.generateParentFromKnowledgeTreeBean(it))
            displayedKnowledgeTrees.addAll(DisplayedKnowledgeTreeBeanFactory.generateChildrenFromKnowledgeTree(it))
        }
        notifyDataSetChanged()
    }

    fun getSpanSize(position: Int): Int {
        return if (displayedKnowledgeTrees[position].type == KnowledgeTreeType.GROUP) 3 else 1
    }
}