package com.wkxjc.wanandroid.me.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wkxjc.wanandroid.common.bean.Todos

class TodoViewModel : ViewModel() {
    var todoStatus = MutableLiveData(0)
    var todos = MutableLiveData<Todos>()
    var dataChanged = MutableLiveData(false)
    var completedTodos = MutableLiveData<Todos>()
}