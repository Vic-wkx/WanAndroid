package com.wkxjc.wanandroid.commonWebSites

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.wkxjc.wanandroid.databinding.FragmentCommonWebsitesBinding
import com.wkxjc.wanandroid.home.common.api.CommonWebsitesApi


class CommonWebsitesFragment : BaseFragment<FragmentCommonWebsitesBinding>() {
//    private var _binding: FragmentCommonWebsitesBinding? = null
//    private val binding get() = _binding!!
//    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
//        _binding = FragmentCommonWebsitesBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun releaseView() {
//        _binding = null
//    }

    private val httpManager = HttpManager(this)
    private val commonWebsitesApi = CommonWebsitesApi()
    private val commonWebsitesAdapter = CommonWebsitesAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            commonWebsitesAdapter.refresh(commonWebsitesApi.convert(result))
        }

        override fun onError(error: Throwable) {
        }

    }

    override fun initView() {
        binding.rvCommonWebsites.layoutManager = LinearLayoutManager(context)
        binding.rvCommonWebsites.adapter = commonWebsitesAdapter
    }

    override fun initData() {
        httpManager.request(commonWebsitesApi, listener)
    }
}
