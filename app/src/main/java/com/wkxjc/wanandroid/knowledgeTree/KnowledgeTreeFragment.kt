package com.wkxjc.wanandroid.knowledgeTree

import androidx.recyclerview.widget.GridLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.databinding.FragmentKnowledgeTreeBinding
import com.wkxjc.wanandroid.common.api.KnowledgeTreeApi

const val SPAN_COUNT = 4

class KnowledgeTreeFragment : BaseFragment<FragmentKnowledgeTreeBinding>() {

    private val httpManager = HttpManager(this)
    private val knowledgeTreeApi = KnowledgeTreeApi()
    private val knowledgeTreeAdapter = KnowledgeTreeAdapter()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            knowledgeTreeAdapter.refresh(knowledgeTreeApi.convert(result))
            statusView.setStatus(Status.NORMAL)
        }

        override fun onError(error: Throwable) {
            statusView.setStatus(Status.ERROR)
        }
    }

    override fun initView() {
        val gridLayoutManager = GridLayoutManager(context, SPAN_COUNT)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return knowledgeTreeAdapter.getSpanSize(position)
            }
        }
        statusView.setOnRetryBtnClickListener {
            initData()
        }
        binding.rvKnowledgeTree.layoutManager = gridLayoutManager
        binding.rvKnowledgeTree.adapter = knowledgeTreeAdapter
    }

    override fun initData() {
        statusView.setStatus(Status.LOADING)
        httpManager.request(knowledgeTreeApi, listener)
    }

}
