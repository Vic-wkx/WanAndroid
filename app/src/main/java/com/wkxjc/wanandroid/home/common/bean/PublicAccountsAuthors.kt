package com.wkxjc.wanandroid.home.common.bean

data class PublicAccountsAuthors(val data: MutableList<PublicAccountsAuthorBean> = mutableListOf()) {

    fun refresh(publicAccountsAuthors: List<PublicAccountsAuthorBean>) {
        this.data.clear()
        this.data.addAll(publicAccountsAuthors)
    }
}