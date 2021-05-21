package com.wkxjc.wanandroid.me

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.wkxjc.wanandroid.common.user.IS_LOGIN
import com.wkxjc.wanandroid.common.user.LOGIN_INFO
import com.wkxjc.wanandroid.common.user.NonTourist
import com.wkxjc.wanandroid.common.user.Tourist

class MeViewModel : ViewModel() {
    var user = MutableLiveData(if (SPUtils.getInstance(LOGIN_INFO).getBoolean(IS_LOGIN)) NonTourist() else Tourist())
}