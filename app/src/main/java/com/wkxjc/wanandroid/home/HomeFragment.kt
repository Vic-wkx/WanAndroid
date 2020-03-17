package com.wkxjc.wanandroid.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.wkxjc.wanandroid.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_home

    override fun initView() {
        rvHome.layoutManager = LinearLayoutManager(requireContext())
        rvHome.adapter = HomeAdapter(listOf("data"))
    }

    override fun initData() {
    }
}