package com.wkxjc.wanandroid.me.user

import com.base.library.project.BaseActivity
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.collection.CollectionActivity
import com.wkxjc.wanandroid.me.todo.TodoActivity
import kotlinx.android.synthetic.main.activity_user.*
import org.jetbrains.anko.startActivity

class UserActivity : BaseActivity() {
    override fun layoutId() = R.layout.activity_user

    override fun initView() {
        btnMyTodo.setOnClickListener {
            startActivity<TodoActivity>()
        }
        btnMyCollection.setOnClickListener {
            startActivity<CollectionActivity>()
        }
        btnExitLogin.setOnClickListener {
            User.loginOut()
            finish()
        }
    }

    override fun initData() {
    }
}