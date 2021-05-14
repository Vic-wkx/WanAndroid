package com.wkxjc.wanandroid.publicAccounts

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.api.BaseApi
import com.base.library.rxRetrofit.http.httpList.HttpListConfig
import com.base.library.rxRetrofit.http.httpList.HttpListListener
import com.base.library.rxRetrofit.http.listener.HttpListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsBinding
import com.wkxjc.wanandroid.home.common.api.PublicAccountsArticlesApi
import com.wkxjc.wanandroid.home.common.api.PublicAccountsAuthorsApi
import com.wkxjc.wanandroid.home.common.bean.Articles
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthorBean
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthors


class PublicAccountsFragment : BaseFragment<FragmentPublicAccountsBinding>() {

    private val viewModel by viewModels<PublicAccountsViewModel>()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }

    private val httpManager = HttpManager(this)
    private val publicAccountsAuthorsApi = PublicAccountsAuthorsApi()
    private val publicAccountArticlesApi by lazy { PublicAccountsArticlesApi() }

    override fun initView() {
        viewModel.status.observe(this) {
            statusView.setStatus(it)
        }
    }

    override fun initData() {
        viewModel.status.value = Status.LOADING
        httpManager.request(arrayOf(publicAccountsAuthorsApi, publicAccountArticlesApi), object : HttpListListener() {
            override fun onSingleNext(api: BaseApi, result: String): Any {
                return if (api is PublicAccountsAuthorsApi) {
                    val authors = api.convert(result)
                    Log.d("~~~", "authors:${authors.size}, ${authors.first().id}")
                    if (authors.isNotEmpty()) {
                        publicAccountArticlesApi.id = authors.first().id
                    }
                    authors
                } else {
                    (api as PublicAccountsArticlesApi).convert(result)
                }
            }

            override fun onNext(resultMap: HashMap<BaseApi, Any>) {
                val authors = resultMap[publicAccountsAuthorsApi] as List<PublicAccountsAuthorBean>
                val articles = resultMap[publicAccountArticlesApi] as Articles
                viewModel.publicAccountsAuthors.refresh(authors)
                viewModel.articles.refresh(articles)
                Log.d("~~~", "refresh public accounts")
                viewModel.status.value = Status.NORMAL
            }

            override fun onError(error: Throwable) {
                viewModel.status.value = Status.ERROR
            }
        }, HttpListConfig(order = true))
    }
}
