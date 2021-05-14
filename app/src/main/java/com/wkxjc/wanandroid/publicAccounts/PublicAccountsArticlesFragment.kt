package com.wkxjc.wanandroid.publicAccounts

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsArticlesBinding
import com.wkxjc.wanandroid.home.common.api.PublicAccountsArticleApi
import com.wkxjc.wanandroid.publicAccounts.publicAccountsArticles.PublicAccountsArticlesAdapter

class PublicAccountsArticlesFragment : BaseFragment<FragmentPublicAccountsArticlesBinding>() {
//    private var _binding: FragmentPublicAccountsArticlesBinding? = null
//    private val binding get() = _binding!!
//    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
//        _binding = FragmentPublicAccountsArticlesBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//    override fun releaseView() {
//        _binding = null
//    }

    private val viewModel by viewModels<PublicAccountsViewModel>()

    private val httpManager = HttpManager(this)
    private val publicAccountArticleApi by lazy { PublicAccountsArticleApi() }
    private val publicAccountArticlesAdapter = PublicAccountsArticlesAdapter()
    private val listener = object : HttpListener() {
        override fun onNext(result: String) {
            viewModel.authorId2ArticlesMap.value!![publicAccountArticleApi.id] = publicAccountArticleApi.convert(result)
            viewModel.status.value = Status.NORMAL
        }

        override fun onError(error: Throwable) {
            viewModel.status.value = Status.ERROR
        }
    }

    override fun initView() {
        binding.rvPublicAccountArticles.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountArticles.adapter = publicAccountArticlesAdapter
        viewModel.publicAccountsAuthors.observe(this) {
            Log.d("~~~", "accounts.data.isNotEmpty():${it.data.isNotEmpty()}")
            if (it.data.isNotEmpty()) {
                publicAccountArticleApi.id = it.data.first().id
                httpManager.request(publicAccountArticleApi, listener)
            } else {
                viewModel.status.value = Status.EMPTY
            }
        }
        viewModel.authorId2ArticlesMap.observe(this) {
            publicAccountArticlesAdapter.refresh(it[publicAccountArticleApi.id]!!)
        }
    }

    override fun initData() {
    }
}