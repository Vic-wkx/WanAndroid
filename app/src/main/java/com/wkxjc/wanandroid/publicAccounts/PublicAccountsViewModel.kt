package com.wkxjc.wanandroid.publicAccounts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lewis.widget.ui.Status
import com.wkxjc.wanandroid.home.common.bean.Articles
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthors

class PublicAccountsViewModel : ViewModel() {
    val status = MutableLiveData<Status>()
    val publicAccountsAuthors = MutableLiveData<PublicAccountsAuthors>(PublicAccountsAuthors())
    val authorId2ArticlesMap = MutableLiveData<HashMap<Int, Articles>>()
}