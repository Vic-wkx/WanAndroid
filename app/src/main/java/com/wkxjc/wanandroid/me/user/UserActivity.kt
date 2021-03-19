package com.wkxjc.wanandroid.me.user

import com.base.library.project.BaseActivity
import com.base.library.project.myStartActivity
import com.wkxjc.wanandroid.databinding.ActivityUserBinding
import com.wkxjc.wanandroid.me.user.collection.CollectionActivity
import com.wkxjc.wanandroid.me.user.todo.TodoActivity

class UserActivity : BaseActivity() {
    private val binding by lazy { ActivityUserBinding.inflate(layoutInflater) }
    override fun createBinding() = binding.root
    override fun initView() {
        binding.btnMyTodo.setOnClickListener {
            myStartActivity<TodoActivity>()
        }
        binding.btnMyCollection.setOnClickListener {
            myStartActivity<CollectionActivity>()
        }
        binding.btnExitLogin.setOnClickListener {
            User.loginOut()
            finish()
        }
    }

    override fun initData() {
    }
}