package com.wkxjc.wanandroid.home.common.bean

data class PublicAccounts(val data: MutableList<PublicAccountBean> = mutableListOf()) {

    fun refresh(accounts: List<PublicAccountBean>) {
        this.data.clear()
        this.data.addAll(accounts)
    }
}