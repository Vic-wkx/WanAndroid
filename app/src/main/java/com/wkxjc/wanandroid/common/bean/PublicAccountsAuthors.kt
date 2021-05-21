package com.wkxjc.wanandroid.common.bean

data class PublicAccountsAuthors(val data: MutableList<PublicAccountsAuthorBean> = mutableListOf()) {

    fun refresh(publicAccountsAuthors: List<PublicAccountsAuthorBean>) {
        this.data.clear()
        this.data.addAll(publicAccountsAuthors)
    }
}