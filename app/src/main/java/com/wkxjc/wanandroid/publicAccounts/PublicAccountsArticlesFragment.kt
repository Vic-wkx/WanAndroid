package com.wkxjc.wanandroid.publicAccounts

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.library.project.BaseFragment
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsArticlesBinding
import com.wkxjc.wanandroid.publicAccounts.publicAccountsArticles.PublicAccountsArticlesAdapter

class PublicAccountsArticlesFragment : BaseFragment<FragmentPublicAccountsArticlesBinding>() {

    private val viewModel by viewModels<PublicAccountsViewModel>()

//    private val httpManager = HttpManager(this)
//    private val publicAccountArticleApi by lazy { PublicAccountsArticleApi() }
     val publicAccountArticlesAdapter by lazy { PublicAccountsArticlesAdapter(viewModel.articles) }
//    private val publicAccountArticlesAdapter = PublicAccountsArticlesAdapter()
//    private val listener = object : HttpListener() {
//        override fun onNext(result: String) {
//            Log.d("~~~", "get articles")
//            viewModel.authorId2ArticlesMap.value!![publicAccountArticleApi.id] = publicAccountArticleApi.convert(result)
//            viewModel.status.value = Status.NORMAL
//        }
//
//        override fun onError(error: Throwable) {
//            viewModel.status.value = Status.ERROR
//        }
//    }

    override fun initView() {
        binding.rvPublicAccountArticles.layoutManager = LinearLayoutManager(context)
        binding.rvPublicAccountArticles.adapter = publicAccountArticlesAdapter
//        viewModel.publicAccountsAuthors.data.observe(this) {
//            Log.d("~~~", "author updated, is data empty:${it.isEmpty()}")
//            if (it.isNotEmpty()) {
//                publicAccountArticleApi.id = it.first().id
//                httpManager.request(publicAccountArticleApi, listener)
//            } else {
//                viewModel.status.value = Status.EMPTY
//            }
//        }
//        viewModel.authorId2ArticlesMap.observe(this) {
//            Log.d("~~~", "articles refresh")
//            publicAccountArticlesAdapter.refresh(it[publicAccountArticleApi.id]!!)
//        }
    }

    override fun initData() {
    }
}