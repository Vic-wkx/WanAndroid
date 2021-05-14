package com.wkxjc.wanandroid.publicAccounts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wkxjc.wanandroid.home.common.bean.PublicAccountsAuthors

class PublicAccountsViewModel : ViewModel() {
    val publicAccountsAuthors = MutableLiveData<PublicAccountsAuthors>()
}