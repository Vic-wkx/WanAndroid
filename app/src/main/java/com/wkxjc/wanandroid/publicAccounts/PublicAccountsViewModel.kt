package com.wkxjc.wanandroid.publicAccounts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lewis.widget.ui.Status
import com.wkxjc.wanandroid.home.common.bean.Articles
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthors
import java.util.*

class PublicAccountsViewModel : ViewModel() {
    val publicAccountsStatus = MutableLiveData<Status>()
    val publicAccountsArticlesStatus = MutableLiveData<Status>()
    var publicAccountsAuthors = MutableLiveData<PublicAccountsAuthors>()
    val publicAccountsArticles = MutableLiveData<Articles>()
    val currentPublicAccountsAuthorId = MutableLiveData<Int>()
    val currentPublicAccountsArticlesPage = MutableLiveData<Int>()
    val authorId2ArticlesMap = MutableLiveData<HashMap<Int, Articles>>()
}