package com.wkxjc.wanandroid.me

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.library.project.BaseFragment
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.wkxjc.wanandroid.databinding.FragmentMeBinding
import com.wkxjc.wanandroid.me.collection.CollectionActivity
import com.wkxjc.wanandroid.me.common.api.LOGIN_INFO
import com.wkxjc.wanandroid.me.login.LoginActivity
import com.wkxjc.wanandroid.me.todo.TodoActivity


class MeFragment : BaseFragment() {

    private var _binding: FragmentMeBinding? = null
    private val binding get() = _binding!!
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentMeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun releaseBinding() {
        _binding = null
    }

    override fun initView() {
        binding.tvLogin.setOnClickListener {
            myStartActivity<LoginActivity>()
        }
        binding.btnMyTodo.setOnClickListener {
            myStartActivity<TodoActivity>()
        }
        binding.btnMyCollection.setOnClickListener {
            myStartActivity<CollectionActivity>()
        }
        binding.btnExitLogin.setOnClickListener {
            SPUtils.getInstance(LOGIN_INFO).clear()
        }
    }

    override fun initData() {
    }
}