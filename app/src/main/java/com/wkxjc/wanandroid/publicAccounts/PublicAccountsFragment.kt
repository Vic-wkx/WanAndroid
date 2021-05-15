package com.wkxjc.wanandroid.publicAccounts

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.base.library.project.BaseFragment
import com.base.library.rxRetrofit.http.HttpManager
import com.base.library.rxRetrofit.http.api.BaseApi
import com.base.library.rxRetrofit.http.httpList.HttpListConfig
import com.base.library.rxRetrofit.http.httpList.HttpListListener
import com.lewis.widget.ui.Status
import com.lewis.widget.ui.view.StatusView
import com.wkxjc.wanandroid.databinding.FragmentPublicAccountsBinding
import com.wkxjc.wanandroid.home.common.api.PublicAccountsArticlesApi
import com.wkxjc.wanandroid.home.common.api.PublicAccountsAuthorsApi
import com.wkxjc.wanandroid.home.common.bean.Articles
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthorBean


class PublicAccountsFragment : BaseFragment<FragmentPublicAccountsBinding>() {

    private val viewModel by activityViewModels<PublicAccountsViewModel>()
    private val statusView by lazy { StatusView.initInFragment(context, binding.root) }
    private val httpManager = HttpManager(this)
    private val publicAccountsAuthorsApi = PublicAccountsAuthorsApi()
    private val publicAccountArticlesApi by lazy { PublicAccountsArticlesApi() }
    private val httpListListener = object : HttpListListener() {
        override fun onSingleNext(api: BaseApi, result: String): Any {
            return if (api is PublicAccountsAuthorsApi) {
                val authors = api.convert(result)
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
            viewModel.status.value = Status.NORMAL
        }

        override fun onError(error: Throwable) {
            viewModel.status.value = Status.ERROR
        }
    }

    override fun initView() {
        viewModel.status.observe(this) {
            statusView.setStatus(it)
        }
    }

    override fun initData() {
        viewModel.status.value = Status.LOADING
        httpManager.request(arrayOf(publicAccountsAuthorsApi, publicAccountArticlesApi), httpListListener, HttpListConfig(order = true))
    }
}
