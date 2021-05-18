package com.wkxjc.wanandroid.me

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MeViewModel : ViewModel() {
    var user = MutableLiveData<User>()
}