package com.wkxjc.wanandroid.me

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.library.rxRetrofit.common.utils.SPUtils

class MeViewModel : ViewModel() {
    var user = MutableLiveData(if (SPUtils.getInstance(LOGIN_INFO).getBoolean(IS_LOGIN)) NonTourist() else Tourist())
}